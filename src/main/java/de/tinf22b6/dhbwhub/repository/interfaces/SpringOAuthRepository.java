package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.OAuthAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringOAuthRepository extends JpaRepository<OAuthAccount, Long> {

    Optional<OAuthAccount> findByAccountId(Long accountId);
}
