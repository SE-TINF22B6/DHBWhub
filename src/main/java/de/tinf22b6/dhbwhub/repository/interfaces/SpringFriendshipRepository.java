package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringFriendshipRepository extends JpaRepository<Friendship,Long> {
}
