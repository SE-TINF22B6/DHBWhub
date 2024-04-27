package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpringUserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM client_user WHERE account_id = ?1", nativeQuery = true)
    User findByAccoundId(Long accoundId);
}
