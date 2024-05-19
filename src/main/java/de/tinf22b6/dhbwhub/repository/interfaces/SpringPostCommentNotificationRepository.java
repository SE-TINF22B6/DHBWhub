package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.notification_tables.PostCommentNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringPostCommentNotificationRepository extends JpaRepository<PostCommentNotification, Long> {
    List<PostCommentNotification> findByAccumulatedId(Long accumulatedId);

    @Query(value = "SELECT * FROM post_comment_notification WHERE seen = false AND user_id = ?1", nativeQuery = true)
    List<PostCommentNotification> findUnseenNotifications(Long userId);
}
