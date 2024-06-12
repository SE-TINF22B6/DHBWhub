package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.NotificationMapper;
import de.tinf22b6.dhbwhub.mapper.PictureMapper;
import de.tinf22b6.dhbwhub.mapper.PostMapper;
import de.tinf22b6.dhbwhub.mapper.PostTagMapper;
import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.model.PostTag;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtablePost;
import de.tinf22b6.dhbwhub.model.notification_tables.PostLikeNotification;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;
import de.tinf22b6.dhbwhub.repository.*;
import de.tinf22b6.dhbwhub.service.interfaces.PostService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository repository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final PostTagRepository postTagRepository;
    private final LogtableRepository logtableRepository;
    private final NotificationRepository notificationRepository;
    private final CommentRepository commentRepository;
    private final EmailService emailService;

    public PostServiceImpl(@Autowired PostRepository repository,
                           @Autowired UserRepository userRepository,
                           @Autowired PictureRepository pictureRepository,
                           @Autowired PostTagRepository postTagRepository,
                           @Autowired LogtableRepository logtableRepository,
                           @Autowired NotificationRepository notificationRepository,
                           @Autowired CommentRepository commentRepository,
                           @Autowired EmailService emailService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.postTagRepository = postTagRepository;
        this.logtableRepository = logtableRepository;
        this.notificationRepository = notificationRepository;
        this.commentRepository = commentRepository;
        this.emailService = emailService;
    }

    @Value("${spring.mail.support-mail}")
    private String supportMail;

    @Override
    public List<Post> getAll() {
        return repository.findAll();
    }

    @Override
    public Post create(PostProposal proposal) {
        Post post = PostMapper.mapToModel(proposal);
        return repository.save(post);
    }

    @Override
    public HomepagePostPreviewProposal create(CreatePostProposal proposal) {
        // Creating the Post itself
        User user = userRepository.findByAccountId(proposal.getAccountId());
        if(user == null){
            throw new NoSuchEntryException("User with the AccountId " + proposal.getAccountId() + " does not exist!");
        }
        Picture picture = !proposal.getPostImage().isEmpty() ?
                pictureRepository.save(PictureMapper.mapToModelPost(proposal.getPostImage())): null;

        Post post = repository.save(PostMapper.mapToModel(proposal,user,picture));

        // Creating Tags after the Post is created
        Arrays.stream(proposal.getTags()).forEach(t -> {
            PostTag postTag = new PostTag(post, t);
            postTagRepository.save(postTag);
        } );

        return getPostHomepageView(post.getId());
    }

    @Override
    public void report(ReportPostProposal proposal) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("post_url", String.format("https://www.dhbwhub.de/%s/?id=%d", proposal.getType().equals("eventComment") ? "event" : "post", proposal.getPostId()));
        templateModel.put("content_type", proposal.getType());
        templateModel.put("comment_id", proposal.getCommentId());
        templateModel.put("user_id_of_reporter", proposal.getUserId());
        templateModel.put("user_id_of_author", proposal.getAuthorId());
        templateModel.put("report_reason", proposal.getReportReason());
        templateModel.put("additional_notes", proposal.getReportDescription());

        try {
            emailService.sendMessageUsingThymeleafTemplate(supportMail, "Incoming User Report", "report-template.html", templateModel);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException("An error occurred while trying to send the report: " + e.getMessage());
        }
    }

    @Override
    public Post get(Long id) {
        Post post = repository.find(id);
        if (post == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", Post.class.getSimpleName(), id));
        }
        return post;
    }

    @Override
    public Post update(Long id, PostProposal proposal) {
        // Check if post exists
        get(id);

        Post post = PostMapper.mapToModel(proposal);
        post.setId(id);
        return repository.save(post);
    }

    @Override
    public int increaseLikes(LikePostProposal likePostProposal) {
        Post post = get(likePostProposal.getPostId());
        int likes = post.getLikes() + 1;
        Post updatedPost = PostMapper.mapToModel(post,likes);
        updatedPost.setId(likePostProposal.getPostId());

        // Log into Liketable
        User user = userRepository.find(likePostProposal.getUserId());
        LikeLogtablePost likeLogtablePost = new LikeLogtablePost(user, post);
        if(logtableRepository.checkIfPostExists(post.getId(), user.getId())){
            throw new EntityExistsException("Entity already exists!");
        }
        logtableRepository.savePost(likeLogtablePost);

        // Create Notification for Post-author
        if(!Objects.equals(post.getUser().getId(), user.getId()) &&
                !notificationRepository.checkIfPostLikeEntryExists(user.getId(), post.getId())){
            PostLikeNotification notification = NotificationMapper.mapToPostLikeNotification(post, user);
            notification.setAccumulatedId(null);
            notificationRepository.savePostLikeNotification(notification);
        }

        return repository.save(updatedPost).getLikes();
    }

    @Override
    public int decreaseLikes(LikePostProposal likePostProposal) {
        Post post = get(likePostProposal.getPostId());
        int likes = post.getLikes() - 1;
        Post updatedPost = PostMapper.mapToModel(post,likes);
        updatedPost.setId(likePostProposal.getPostId());

        // Log into Liketable
        LikeLogtablePost likeLogtablePost = logtableRepository.findPost(likePostProposal.getPostId(), likePostProposal.getUserId());
        if (likeLogtablePost == null) {
            throw new NoSuchEntryException("Entity not logged!");
        }
        logtableRepository.deletePost(likeLogtablePost.getId());

        return repository.save(updatedPost).getLikes();
    }

    @Override
    public boolean isLiked(LikePostProposal likePostProposal) {
        return logtableRepository.findPost(likePostProposal.getPostId(), likePostProposal.getUserId()) != null;
    }

    @Override
    public PostThreadViewProposal update(Long id, UpdatePostProposal proposal) {
        Post initialPost = get(id);
        Picture picture;

        String proposalImageData = proposal.getPostImage() != null ? proposal.getPostImage() : "";
        String initialImageData = initialPost.getPicture() != null ? initialPost.getPicture().getImageData() : "";
        // Check if Picture has changed
        if (proposalImageData.isEmpty() && !initialImageData.isEmpty()) {
            pictureRepository.delete(initialPost.getPicture().getId());
            picture = null;
        }
        else if (!initialImageData.equals(proposalImageData)) {
            assert initialPost.getPicture() != null;
            pictureRepository.delete(initialPost.getPicture().getId());
            picture = pictureRepository.save(PictureMapper.mapToModelPost(proposalImageData));
        } else {
            picture = initialPost.getPicture();
        }

        // Update post
        Post updatedPost = PostMapper.mapToModel(proposal, initialPost, picture);
        updatedPost.setId(id);

        Post post = repository.save(updatedPost);

        /* Check if Tags changed
            formerTags = Tags in the database
            proposedTags = Tags proposed
        * */
        List<PostTag> formerTags = postTagRepository.findByPostId(id);
        List<String> proposedTags = new ArrayList<>(Arrays.stream(proposal.getTags()).toList());

        for (PostTag postTag : formerTags) {
            if (proposedTags.contains(postTag.getTag())) {
                proposedTags.remove(postTag.getTag());
            } else {
                postTagRepository.delete(postTag.getId());
            }
        }
        proposedTags.forEach(t -> {
            if( t != null) postTagRepository.save(PostTagMapper.mapToModel(post, t));
        });

        return getPostThreadView(updatedPost.getId());
    }

    @Override
    public void delete(Long id) {
        // Check if post exists
        get(id);

        // Delete all like entries
        logtableRepository.findAllPosts().stream().filter(p -> p.getPost().getId().equals(id)).forEach(p -> {
            LikeLogtablePost likeLogtablePost = logtableRepository.findPost(id, p.getUser().getId());
            logtableRepository.deletePost(likeLogtablePost.getId());
        });
        // Delete all post like notifications
        notificationRepository.getAllPostLikeNotifications().stream().filter(n -> n.getPostId().equals(id)).forEach(notificationRepository::deletePostLikeNotification);
        // Delete all post comment notifications
        notificationRepository.getAllPostCommentNotifications().stream().filter(n -> n.getPostId().equals(id)).forEach(notificationRepository::deletePostCommentNotification);
        // Delete all comment like notifications
        notificationRepository.getAllCommentLikeNotifications().stream().filter(n -> n.getPostId().equals(id)).forEach(notificationRepository::deleteCommentLikeNotification);

        // Delete all related Comments first
        commentRepository.findByPostId(id).forEach(comment -> commentRepository.delete(comment.getId()));
        // Delete all Tags
        postTagRepository.findByPostId(id).forEach(postTag -> postTagRepository.delete(postTag.getId()));

        repository.delete(id);
    }

    @Override
    public int getAmountOfComments(Long id) {
        return repository.getAmountOfComments(id);
    }

    @Override
    public List<HomepagePostPreviewProposal> getHomepagePosts() {
        List<HomepagePostPreviewProposal> homepagePostPreviewProposals = repository.findHomepagePosts().stream().map(PostMapper::mapToHomepagePreviewProposal).toList();
        homepagePostPreviewProposals.forEach(p -> {
            p.setCommentAmount(getAmountOfComments(p.getId()));
            p.setTags(getPostTags(p.getId()));
        });
        return homepagePostPreviewProposals;
    }

    @Override
    public List<HomepagePostPreviewProposal> getFacPosts(Long id) {
       List<HomepagePostPreviewProposal> posts;
       switch (id.intValue()) {
            case 0 -> posts = repository.findPostsFromFacTechnik().stream().map(PostMapper::mapToHomepagePreviewProposal).toList();
            case 1 -> posts = repository.findPostsFromFacGesundheit().stream().map(PostMapper::mapToHomepagePreviewProposal).toList();
            case 2 -> posts = repository.findPostsFromFacWirtschaft().stream().map(PostMapper::mapToHomepagePreviewProposal).toList();
            default -> posts = repository.findHomepagePosts().stream().map(PostMapper::mapToHomepagePreviewProposal).toList();
        }
       posts.forEach(p -> {
            p.setCommentAmount(getAmountOfComments(p.getId()));
            p.setTags(getPostTags(p.getId()));
       });
       return posts;
    }

    @Override
    public PostThreadViewProposal getPostThreadView(Long id) {
        PostThreadViewProposal postThreadViewProposal = PostMapper.mapToPostThreadViewProposal(get(id));

        postThreadViewProposal.setTags(getPostTags(id));
        postThreadViewProposal.setCommentAmount(getAmountOfComments(id));
        postThreadViewProposal.setComments(getPostComments(id));

        return postThreadViewProposal;
    }

    @Override
    public HomepagePostPreviewProposal getPostHomepageView(Long id) {
        HomepagePostPreviewProposal homepagePostPreviewProposal = PostMapper.mapToHomepagePreviewProposal(get(id));
        homepagePostPreviewProposal.setTags(getPostTags(id));
        homepagePostPreviewProposal.setCommentAmount(getAmountOfComments(id));

        return homepagePostPreviewProposal;
    }
    @Override
    public List<CommentThreadViewProposal> getPostComments(Long id) {
        return repository.getPostComments(id);
    }

    @Override
    public List<String> getPostTags(Long id) {
        return repository.getPostTags(id);
    }

    @Override
    public List<String> getPopularPostTags() {
        return postTagRepository.findPopularTags();
    }

    @Override
    public List<HomepagePostPreviewProposal> getPostsFromUser(Long id) {
        List<HomepagePostPreviewProposal> userPosts = repository.findPostsFromUser(id).stream().map(PostMapper::mapToHomepagePreviewProposal).toList();
        userPosts.forEach(p -> {
            p.setCommentAmount(getAmountOfComments(p.getId()));
            p.setTags(getPostTags(p.getId()));
        });
        return userPosts;
    }

    @Override
    public List<HomepagePostPreviewProposal> getPostsFromFriends(Long id) {
        List<HomepagePostPreviewProposal> friendPosts = repository.findPostsFromFriends(id).stream().map(PostMapper::mapToHomepagePreviewProposal).toList();
        friendPosts.forEach(p -> {
            p.setCommentAmount(getAmountOfComments(p.getId()));
            p.setTags(getPostTags(p.getId()));
        });
        return friendPosts;
    }

    @Override
    public List<HomepagePostPreviewProposal> getPostsByTag(String tag) {
        List<HomepagePostPreviewProposal> posts = repository.findPostsByTag(tag).stream().map(PostMapper::mapToHomepagePreviewProposal).toList();
        posts.forEach(p -> {
            p.setCommentAmount(getAmountOfComments(p.getId()));
            p.setTags(getPostTags(p.getId()));
        });
        return posts;
    }

    @Override
    public List<HomepagePostPreviewProposal> getPostsByKeyword(String keyword) {
        List<HomepagePostPreviewProposal> posts = repository.findPostsByKeyword(keyword).stream().map(PostMapper::mapToHomepagePreviewProposal).toList();
        posts.forEach(p -> {
            p.setCommentAmount(getAmountOfComments(p.getId()));
            p.setTags(getPostTags(p.getId()));
        });
        return posts;
    }

    @Override
    public List<HomepagePostPreviewProposal> getPostTagsByKeyword(String keyword) {
        List<HomepagePostPreviewProposal> posts = repository.findPostsByTagKeyword(keyword).stream().map(PostMapper::mapToHomepagePreviewProposal).toList();
        posts.forEach(p -> {
            p.setCommentAmount(getAmountOfComments(p.getId()));
            p.setTags(getPostTags(p.getId()));
        });
        return posts;
    }

    @Override
    public List<HomepagePostPreviewProposal> getPostsFromFriendsByTag(Long id, String tag) {
        List<HomepagePostPreviewProposal> posts = repository.findPostsFromFriendsByTag(id, tag).stream().map(PostMapper::mapToHomepagePreviewProposal).toList();
        posts.forEach(p -> {
            p.setCommentAmount(getAmountOfComments(p.getId()));
            p.setTags(getPostTags(p.getId()));
        });
        return posts;
    }
}
