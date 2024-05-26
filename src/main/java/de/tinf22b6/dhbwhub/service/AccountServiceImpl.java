package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.AccountMapper;
import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.proposal.AccountProposal;
import de.tinf22b6.dhbwhub.repository.AccountRepository;
import de.tinf22b6.dhbwhub.security.OAuth.CustomOAuth2User;
import de.tinf22b6.dhbwhub.service.interfaces.AccountService;
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

    public boolean checkIfEmailExists(String email) {
        return getAll().stream().anyMatch(account -> account.getEmail().equals(email));
    }

    @Override
    public Account findByEmail(String email) {
        Account account = repository.findByEmail(email);
        if (account == null) {
            throw new NoSuchEntryException(String.format("%s with email %s does not exist", Account.class.getSimpleName(), email));
        }
        return account;
    }


    @Override
    public boolean existsOAuthEntry(Long accountId) {
        return repository.existsOAuthEntry(accountId);
    }

    @Override
    public void processOAuthPostLogin(CustomOAuth2User customOAuth2User) {
        System.out.println(customOAuth2User.getName());
        System.out.println(customOAuth2User.getEmail());
        System.out.println(customOAuth2User.getPicture());

        /*Account account = repository.findByUsername(customOAuth2User.getName()).orElse(null);
        if (account == null) {Account newAccount = AccountMapper.mapToModel(customOAuth2User);
        };
        */
    }

}
