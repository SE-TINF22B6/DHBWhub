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
import de.tinf22b6.dhbwhub.model.logtables.LikeLogtablePost;
import de.tinf22b6.dhbwhub.model.notification_tables.PostLikeNotification;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;
import de.tinf22b6.dhbwhub.repository.*;
import de.tinf22b6.dhbwhub.service.interfaces.PostService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository repository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final PostTagRepository postTagRepository;
    private final LogtableRepository logtableRepository;
    private final NotificationRepository notificationRepository;

    public PostServiceImpl(@Autowired PostRepository repository,
                           @Autowired UserRepository userRepository,
                           @Autowired PictureRepository pictureRepository,
                           @Autowired PostTagRepository postTagRepository,
                           @Autowired LogtableRepository logtableRepository,
                           @Autowired NotificationRepository notificationRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.postTagRepository = postTagRepository;
        this.logtableRepository = logtableRepository;
        this.notificationRepository = notificationRepository;
    }

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
        Picture picture = proposal.getPostImage().length != 0 ?
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
        PostLikeNotification notification = NotificationMapper.mapToPostLikeNotification(post, user);
        notification.setAccumulatedId(null);

        notificationRepository.savePostLikeNotification(notification);

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
    public PostThreadViewProposal update(Long id, UpdatePostProposal proposal) {
        Post initialPost = get(id);
        Picture picture;

        byte[] proposalImageData = proposal.getPostImage() != null? proposal.getPostImage() : new byte[0];
        byte[] initialImageData = initialPost.getPicture() != null? initialPost.getPicture().getImageData() : new byte[0];
        // Check if Picture has changed
        if (proposalImageData.length == 0 && initialImageData.length != 0) {
            pictureRepository.delete(initialPost.getPicture().getId());
            picture = null;
        }
        else if (!Arrays.equals(initialImageData, proposalImageData)) {
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
}
