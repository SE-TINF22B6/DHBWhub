package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringUserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM client_user WHERE account_id = ?1", nativeQuery = true)
    User findByAccountId(Long accoundId);

    @Query(value = "SELECT * FROM client_user WHERE account_id IN (SELECT account_id FROM account WHERE username ILIKE %:keyword%)", nativeQuery = true)
    List<User> findByKeywordIgnoreCase(@Param("keyword") String keyword);

    @Query(value = "SELECT v.follower FROM view_follower_amount v WHERE v.receiver_id = ?1", nativeQuery = true)
    Integer findFollowerAmountByUserId(Long userId);
}
