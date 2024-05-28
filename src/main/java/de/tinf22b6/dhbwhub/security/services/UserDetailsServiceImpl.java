package de.tinf22b6.dhbwhub.security.services;

import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.repository.AccountRepository;
import de.tinf22b6.dhbwhub.repository.AdministratorRepository;
import de.tinf22b6.dhbwhub.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final AdministratorRepository administratorRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found with username: " + username));
        User user = userRepository.findByAccountId(account.getId());

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return UserDetailsImpl.build(user, administratorRepository);
    }

}
