package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtableEventPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringLogtableEventPostRepository extends JpaRepository<LikeLogtableEventPost, Long> {
    Optional<LikeLogtableEventPost> findByEventPostIdAndUserId(Long eventId, Long userId);

    List<LikeLogtableEventPost> findByUserId(Long userId);
}
