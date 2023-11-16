package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.PostMapper;
import de.tinf22b6.dhbwhub.mapper.SavedPostMapper;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.model.SavedPost;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.SavedPostProposal;
import de.tinf22b6.dhbwhub.repository.PostRepository;
import de.tinf22b6.dhbwhub.repository.SavedPostRepository;
import de.tinf22b6.dhbwhub.service.interfaces.PostService;
import de.tinf22b6.dhbwhub.service.interfaces.SavedPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedPostServiceImpl implements SavedPostService {
    private final SavedPostRepository repository;

    public SavedPostServiceImpl(@Autowired SavedPostRepository repository) {
        this.repository = repository;
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
    public void delete(Long id) {
        // Check if post exists
        get(id);

        repository.delete(id);
    }
}
