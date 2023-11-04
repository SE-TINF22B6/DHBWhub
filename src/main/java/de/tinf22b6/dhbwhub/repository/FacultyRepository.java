package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Faculty;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringFacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FacultyRepository {
    private final SpringFacultyRepository repository;

    public FacultyRepository(@Autowired SpringFacultyRepository repository) {
        this.repository = repository;
    }

    public List<Faculty> findAll() {
        return repository.findAll();
    }

    public Faculty find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Faculty save(Faculty faculty) {
        return repository.save(faculty);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
