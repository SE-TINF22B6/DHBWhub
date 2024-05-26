package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringAccountRepository;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringOAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {
    private final SpringAccountRepository repository;
    private final SpringOAuthRepository oAuthRepository;

    public AccountRepository(@Autowired SpringAccountRepository repository,
                             @Autowired SpringOAuthRepository oAuthRepository) {
        this.repository = repository;
        this.oAuthRepository = oAuthRepository;
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

    public Optional<Account> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public Account findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public Boolean existsOAuthEntry(Long accountId) {
        return oAuthRepository.findById(accountId).isEmpty();
    }
}
