package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Administrator;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdministratorRepository {
    private final SpringAdministratorRepository repository;

    public AdministratorRepository(@Autowired SpringAdministratorRepository repository) {
        this.repository = repository;
    }

    public List<Administrator> findAll() {
        return repository.findAll();
    }

    public Administrator find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Administrator save(Administrator administrator) {
        return repository.save(administrator);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
