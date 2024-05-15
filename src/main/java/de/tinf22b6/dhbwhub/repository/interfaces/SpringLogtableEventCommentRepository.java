package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.logtables.LikeLogtableEventComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringLogtableEventCommentRepository extends JpaRepository<LikeLogtableEventComment, Long> {
    Optional<LikeLogtableEventComment> findByEventCommentIdAndUserId(Long eventCommentId, Long userId);

    List<LikeLogtableEventComment> findByUserId(Long userId);
}
