package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtablePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringLogtablePostRepository extends JpaRepository<LikeLogtablePost, Long> {
    Optional<LikeLogtablePost> findByPostIdAndUserId(Long postId, Long userId);

    List<LikeLogtablePost> findByUserId(Long userId);
}
