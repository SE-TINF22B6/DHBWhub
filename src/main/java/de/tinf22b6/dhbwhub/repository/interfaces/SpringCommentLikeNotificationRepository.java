package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.notification_tables.CommentLikeNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringCommentLikeNotificationRepository extends JpaRepository<CommentLikeNotification, Long> {
    List<CommentLikeNotification> findByAccumulatedId(Long accumulatedId);

    @Query(value = "SELECT * FROM comment_like_notification WHERE seen = false AND user_id = ?1", nativeQuery = true)
    List<CommentLikeNotification> findUnseenNotifications(Long userId);

    @Query(value = "SELECT * FROM comment_like_notification WHERE triggering_user_id = ?1 AND post_id = ?2", nativeQuery = true)
    Optional<CommentLikeNotification> findByTriggeringUserIdAndPostId(Long triggeringUserId, Long postId);
}
