package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentRepository {
    private final SpringCommentRepository repository;

    public CommentRepository(@Autowired SpringCommentRepository repository) {
        this.repository = repository;
    }

    public List<Comment> findAll() {
        return repository.findAll();
    }

    public Comment find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
