package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringAccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
