package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostRepository {
    private final SpringPostRepository repository;

    public PostRepository(@Autowired SpringPostRepository repository) {
        this.repository = repository;
    }

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
