package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtablePostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringLogtableCommentRepository extends JpaRepository<LikeLogtablePostComment, Long> {
    Optional<LikeLogtablePostComment> findByCommentIdAndUserId(Long commentId, Long userId);

    List<LikeLogtablePostComment> findByUserId(Long userId);
}
