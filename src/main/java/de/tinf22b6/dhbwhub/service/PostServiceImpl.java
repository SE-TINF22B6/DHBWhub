package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.PostMapper;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.CommentThreadViewProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.HomepagePostPreviewProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.PostThreadViewProposal;
import de.tinf22b6.dhbwhub.repository.PostRepository;
import de.tinf22b6.dhbwhub.service.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository repository;

    public PostServiceImpl(@Autowired PostRepository repository) {
        this.repository = repository;
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
        /*if(postThreadViewProposal == null) {
            return null;
        }*/
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
