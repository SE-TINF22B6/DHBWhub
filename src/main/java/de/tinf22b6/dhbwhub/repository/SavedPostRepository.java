package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.SavedPost;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringSavedPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SavedPostRepository {
    private final SpringSavedPostRepository repository;

    public SavedPostRepository(@Autowired SpringSavedPostRepository repository) {
        this.repository = repository;
    }

    public List<SavedPost> findAll() {
        return repository.findAll();
    }

    public SavedPost find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public SavedPost save(SavedPost post) {
        return repository.save(post);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
