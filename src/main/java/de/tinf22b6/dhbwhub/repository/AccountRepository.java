package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {
    private final SpringAccountRepository repository;

    public AccountRepository(@Autowired SpringAccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Account find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Account save(Account account) {
        return repository.save(account);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
