package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.proposal.AccountProposal;
import de.tinf22b6.dhbwhub.repository.AccountRepository;
import de.tinf22b6.dhbwhub.service.interfaces.AccountService;
import de.tinf22b6.dhbwhub.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    public AccountServiceImpl(@Autowired AccountRepository repository) {
        this.repository = repository;
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
            throw new NoSuchEntryException(Account.class.getSimpleName(), id);
        }
        return account;
    }

    @Override
    public Account update(Long id, AccountProposal proposal) {
        // Check if account exists
        get(id);

        Account account = AccountMapper.mapToModel(proposal);
        account.setAccountId(id);
        return repository.save(account);
    }

    @Override
    public void delete(Long id) {
        // Check if account exists
        get(id);

        repository.delete(id);
    }
}
