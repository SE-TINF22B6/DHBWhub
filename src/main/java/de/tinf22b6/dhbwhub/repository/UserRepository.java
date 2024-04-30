package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final SpringUserRepository repository;

    public UserRepository(@Autowired SpringUserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User findByAccountId(Long accountId) { return repository.findByAccoundId(accountId); }

    public User save(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
