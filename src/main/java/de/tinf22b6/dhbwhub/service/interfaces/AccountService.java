package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.proposal.AccountProposal;
import de.tinf22b6.dhbwhub.security.OAuth.CustomOAuth2User;

import java.util.List;

public interface AccountService {
    List<Account> getAll();
    Account create(AccountProposal proposal);
    boolean checkIfEmailExists(String email);
    Account findByEmail(String email);
    boolean existsOAuthEntry(Long accountId);
    void processOAuthPostLogin(CustomOAuth2User customOAuth2User);
    Account get(Long id);
    Account update(Long id, AccountProposal proposal);
    void delete(Long id);
}
