package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Event;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository {
    private final SpringEventRepository repository;

    public EventRepository(@Autowired SpringEventRepository repository) {
        this.repository = repository;
    }

    public List<Event> findAll() {
        return repository.findAll();
    }

    public Event find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Event save(Event event) {
        return repository.save(event);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
