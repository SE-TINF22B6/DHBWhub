package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.OAuthAccount;
import de.tinf22b6.dhbwhub.proposal.AccountProposal;

import java.util.List;

public interface AccountService {
    List<Account> getAll();
    Account create(AccountProposal proposal);
    Account findByEmail(String email);
    boolean existsOAuthEntry(Long accountId);
    OAuthAccount saveOAuthEntry(OAuthAccount account);
    Account get(Long id);
    Account update(Long id, AccountProposal proposal);
    void delete(Long id);
    boolean checkIfOAuthAccountExists(Long id);
}
