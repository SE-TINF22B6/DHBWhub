package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringPictureRepository;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PictureRepository {
    private final SpringPictureRepository repository;
    private final SpringUserRepository userRepository;

    public PictureRepository(@Autowired SpringPictureRepository repository,
                             @Autowired SpringUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<Picture> findAll() {
        return repository.findAll();
    }

    public Picture find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Picture findByUserId(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        return user.getAccount().getPicture();
    }

    public Picture save(Picture picture) {
        return repository.save(picture);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
