package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

    public List<Comment> findByPostId(Long postId) {
            return repository.findByPostId(postId);
    }

}
