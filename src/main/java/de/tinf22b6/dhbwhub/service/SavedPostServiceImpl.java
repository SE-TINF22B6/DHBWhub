package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.SavedPostMapper;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.model.SavedPost;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.SavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.CreateSavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.DeleteSavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.HomepageSavedPostProposal;
import de.tinf22b6.dhbwhub.repository.PostRepository;
import de.tinf22b6.dhbwhub.repository.SavedPostRepository;
import de.tinf22b6.dhbwhub.repository.UserRepository;
import de.tinf22b6.dhbwhub.service.interfaces.SavedPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedPostServiceImpl implements SavedPostService {
    private final SavedPostRepository repository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public SavedPostServiceImpl(@Autowired SavedPostRepository repository,
        @Autowired UserRepository userRepository,
        @Autowired PostRepository postRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<SavedPost> getAll() {
        return repository.findAll();
    }

    @Override
    public SavedPost create(SavedPostProposal proposal) {
        SavedPost post = SavedPostMapper.mapToModel(proposal);
        return repository.save(post);
    }

    @Override
    public SavedPost get(Long id) {
        SavedPost post = repository.find(id);
        if (post == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", SavedPost.class.getSimpleName(), id));
        }
        return post;
    }

    @Override
    public List<HomepageSavedPostProposal> getSavedPostsByUserId(Long id) {
        return repository.findByUserId(id).stream().map(SavedPostMapper::mapToHomepageProposal).toList();
    }

    @Override
    public HomepageSavedPostProposal createSavedPost(CreateSavedPostProposal proposal) {
        if(proposal == null || proposal.getPostId() == null || proposal.getUserId() == null) {
            throw new IllegalArgumentException("Empty proposal or missing Arguments!");
        }
        User user = userRepository.find(proposal.getUserId());
        Post post = postRepository.find(proposal.getPostId());
        SavedPost savedPost = new SavedPost(user, post);

        return SavedPostMapper.mapToHomepageProposal(repository.save(savedPost));
    }

    @Override
    public void delete(DeleteSavedPostProposal proposal) {
        // Check if post exists
        repository.delete(repository.findByUserIdAndPostId(proposal.getUserId(), proposal.getPostId()).get(0).getId());
    }
}