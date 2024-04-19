package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.PostMapper;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.HomepagePostPreviewProposal;
import de.tinf22b6.dhbwhub.repository.PostRepository;
import de.tinf22b6.dhbwhub.service.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return repository.findAmountOfComments(id);
    }

    @Override
    public List<HomepagePostPreviewProposal> getHomepagePosts() {
        List<HomepagePostPreviewProposal> homepagePostPreviewProposals = getAll().stream().map(PostMapper::mapToHomepagePreviewProposal).collect(Collectors.toList());
        homepagePostPreviewProposals.forEach(p -> p.setAmountComments(getAmountOfComments(p.getId())));
        return homepagePostPreviewProposals;
    }

    @Override
    public List<Post> getFacPosts(int id) {
        return switch (id) {
            case 0 -> repository.findPostsFromFacTechnik();
            case 1 -> repository.findPostsFromFacGesundheit();
            case 2 -> repository.findPostsFromFacWirtschaft();
            default -> repository.findAll();
        };
    }


}
