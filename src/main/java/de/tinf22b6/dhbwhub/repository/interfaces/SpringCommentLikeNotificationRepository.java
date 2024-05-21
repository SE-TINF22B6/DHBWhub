package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.notification_tables.CommentLikeNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringCommentLikeNotificationRepository extends JpaRepository<CommentLikeNotification, Long> {
    List<CommentLikeNotification> findByAccumulatedId(Long accumulatedId);

    @Query(value = "SELECT * FROM comment_like_notification WHERE seen = false AND user_id = ?1", nativeQuery = true)
    List<CommentLikeNotification> findUnseenNotifications(Long userId);
}
