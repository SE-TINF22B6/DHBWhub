package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.CommentMapper;
import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;
import de.tinf22b6.dhbwhub.repository.CommentRepository;
import de.tinf22b6.dhbwhub.service.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;

    public CommentServiceImpl(@Autowired CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comment> getAll() {
        return repository.findAll();
    }

    @Override
    public Comment create(CommentProposal proposal) {
        Comment comment = CommentMapper.mapToModel(proposal);
        return repository.save(comment);
    }

    @Override
    public Comment get(Long id) {
        Comment comment = repository.find(id);
        if (comment == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", Comment.class.getSimpleName(), id));
        }
        return comment;
    }

    @Override
    public Comment update(Long id, CommentProposal proposal) {
        // Check if comment exists
        get(id);

        Comment comment = CommentMapper.mapToModel(proposal);
        comment.setId(id);
        return repository.save(comment);
    }

    @Override
    public void delete(Long id) {
        // Check if comment exists
        get(id);

        repository.delete(id);
    }
}
