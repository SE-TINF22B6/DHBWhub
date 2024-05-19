package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Notification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepository {
    private final SpringNotificationRepository repository;
    private static final String ENTRY_DOESNT_EXIST = "Entry does not exist!";

    public NotificationRepository(@Autowired SpringNotificationRepository repository) {
        this.repository = repository;
    }

    public List<Notification> getAll() { return repository.findAll(); }

    public List<Notification> findPostsByUser(Long id) {
        return repository.findByAccountId(id);
    }

    public Notification find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Notification notification) {
        repository.save(notification);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new EntityNotFoundException(ENTRY_DOESNT_EXIST);
        }
        repository.deleteById(id);
    }
}
