package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringUserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM client_user WHERE account_id = ?1", nativeQuery = true)
    User findByAccoundId(Long accoundId);

    @Query(value = "SELECT * FROM client_user WHERE account_id IN (SELECT account_id WHERE username LIKE '%?1%')", nativeQuery = true)
    List<User> findUsersWithKeyword(String keyword);
}
