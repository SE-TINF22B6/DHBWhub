package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringFriendshipRepository extends JpaRepository<Friendship, Long> {

    @Query(value = "SELECT DISTINCT * FROM friendship WHERE (requester_id = ?1 OR receiver_id = ?1) AND accepted = true", nativeQuery = true)
    List<Friendship> findFriendlist(Long accountId);
}
