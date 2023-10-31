package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PictureRepository {
    private final SpringPictureRepository repository;

    public PictureRepository(@Autowired SpringPictureRepository repository) {
        this.repository = repository;
    }

    public List<Picture> findAll() {
        return repository.findAll();
    }

    public Picture find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Picture save(Picture picture) {
        return repository.save(picture);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
