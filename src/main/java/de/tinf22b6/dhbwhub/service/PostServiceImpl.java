package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.repository.PostRepository;
import de.tinf22b6.dhbwhub.service.interfaces.PostService;
import de.tinf22b6.dhbwhub.utils.mapper.PostMapper;

import java.util.List;

public class PostServiceImpl implements PostService {
    private final PostRepository repository;

    public PostServiceImpl(PostRepository repository) {
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
            throw new IllegalArgumentException(String.format("Post with id %d doesn't exists", id)); // TODO: Replace with custom exception
        }
        return post;
    }

    @Override
    public Post update(Long id, PostProposal proposal) {
        // Check if post exists
        get(id);

        Post post = PostMapper.mapToModel(proposal);
        post.setPostId(id);
        return repository.save(post);
    }

    @Override
    public void delete(Long id) {
        // Check if post exists
        get(id);

        repository.delete(id);
    }
}
