package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringFriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByRequesterId(Long requesterId);
    Optional<Friendship> findByRequesterIdAndReceiverId(Long requesterId, Long receiverId);
}
