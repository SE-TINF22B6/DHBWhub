package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.PictureMapper;
import de.tinf22b6.dhbwhub.mapper.PostMapper;
import de.tinf22b6.dhbwhub.mapper.PostTagMapper;
import de.tinf22b6.dhbwhub.model.*;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.*;
import de.tinf22b6.dhbwhub.repository.*;
import de.tinf22b6.dhbwhub.service.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository repository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final PostTagRepository postTagRepository;

    public PostServiceImpl(@Autowired PostRepository repository,
                           @Autowired UserRepository userRepository,
                           @Autowired PictureRepository pictureRepository,
                           @Autowired PostTagRepository postTagRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.postTagRepository = postTagRepository;
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
    public PostThreadViewProposal create(CreatePostProposal proposal) {
        // Creating the Post itself
        User user = userRepository.findByAccountId(proposal.getAccountId());
        Picture picture = proposal.getPostImage().length != 0 ?
                pictureRepository.save(PictureMapper.mapToModelPost(proposal.getPostImage())): null;

        Post post = repository.save(PostMapper.mapToModel(proposal,user,picture));

        // Creating Tags after the Post is created
        proposal.getTags().forEach(t -> {
            new Post();
            PostTag postTag = new PostTag(post, t);
            postTagRepository.save(postTag);
        } );

        return getPostThreadView(post.getId());
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
    public int increaseLikes(Long id) {
        Post post = get(id);
        int likes = post.getLikes() + 1;
        Post updatedPost = repository.save(PostMapper.mapToModel(post,likes));
        return updatedPost.getLikes();
    }

    @Override
    public int decreaseLikes(Long id) {
        Post post = get(id);
        int likes = post.getLikes() - 1;
        Post updatedPost = repository.save(PostMapper.mapToModel(post,likes));
        return updatedPost.getLikes();
    }

    @Override
    public PostThreadViewProposal update(Long id, UpdatePostProposal proposal) {
        Post initialPost = get(id);
        Picture picture;

        // Check if Picture has changed
        if(!Arrays.equals(initialPost.getPicture().getImageData(), proposal.getPostImage())){
            pictureRepository.delete(initialPost.getPicture().getId());
            picture = pictureRepository.save(PictureMapper.mapToModelPost(proposal.getPostImage()));
        } else {
            picture = PictureMapper.mapToModelComment(initialPost.getPicture().getImageData());
        }

        // Update post
        Post updatedPost = PostMapper.mapToModel(proposal, initialPost, picture);
        repository.save(updatedPost);

        /* Check if Tags changed
            formerTags = Tags in the database
            proposedTags = Tags proposed
            updatedTags = Tags, which will be delivered to requester
        * */
        List<PostTag> formerTags = postTagRepository.findByPostId(initialPost.getId());
        List<String> proposedTags = proposal.getTags();

        for (PostTag postTag : formerTags) {
            if (proposedTags.contains(postTag.getTag())) {
                proposedTags.remove(postTag.getTag());
            } else {
                postTagRepository.delete(postTag.getId());
            }
        }
        proposedTags.forEach(t -> postTagRepository.save(PostTagMapper.mapToModel(updatedPost, t)));

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
    public List<CommentThreadViewProposal> getPostComments(Long id) {
        return repository.getPostComments(id);
    }

    @Override
    public List<String> getPostTags(Long id) {
        return repository.getPostTags(id);
    }


}
