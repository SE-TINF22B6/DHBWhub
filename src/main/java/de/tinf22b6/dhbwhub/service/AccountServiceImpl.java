package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.AccountMapper;
import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.proposal.AccountProposal;
import de.tinf22b6.dhbwhub.repository.AccountRepository;
import de.tinf22b6.dhbwhub.repository.UserRepository;
import de.tinf22b6.dhbwhub.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final UserRepository userRepository;
    public AccountServiceImpl(@Autowired AccountRepository repository, @Autowired UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public Account create(AccountProposal proposal) {
        Account account = AccountMapper.mapToModel(proposal);
        return repository.save(account);
    }

    @Override
    public Account get(Long id) {
        Account account = repository.find(id);
        if (account == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", Account.class.getSimpleName(), id));
        }
        return account;
    }

    @Override
    public Account update(Long id, AccountProposal proposal) {
        // Check if account exists
        get(id);

        Account account = AccountMapper.mapToModel(proposal);
        account.setId(id);
        return repository.save(account);
    }

    @Override
    public void delete(Long id) {
        // Check if account exists
        get(id);

        repository.delete(id);
    }

    @Override
    public Long getUserIdByAccountId(Long id) {
        return userRepository.findByAccountId(id).getId();
    }

    @Override
    public Account findByEmail(String email) {
        return repository.findByEmail(email);
    }


}
