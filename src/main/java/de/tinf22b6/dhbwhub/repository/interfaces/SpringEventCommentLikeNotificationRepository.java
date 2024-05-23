package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.notification_tables.EventCommentLikeNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringEventCommentLikeNotificationRepository extends JpaRepository<EventCommentLikeNotification, Long> {
    List<EventCommentLikeNotification> findByAccumulatedId(Long accumulatedId);

    @Query(value = "SELECT * FROM event_comment_like_notification WHERE seen = false AND user_id = ?1", nativeQuery = true)
    List<EventCommentLikeNotification> findUnseenNotifications(Long userId);
}
