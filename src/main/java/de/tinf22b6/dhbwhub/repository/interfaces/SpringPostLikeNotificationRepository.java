package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.notification_tables.PostLikeNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringPostLikeNotificationRepository extends JpaRepository<PostLikeNotification, Long> {
    List<PostLikeNotification> findByAccumulatedId(Long accumulatedId);

    @Query(value = "SELECT * FROM post_like_notification WHERE seen = false AND user_id = ?1", nativeQuery = true)
    List<PostLikeNotification> findUnseenNotifications(Long userId);

    @Query(value = "SELECT * FROM post_like_notification WHERE triggering_user_id = ?1 AND post_id = ?2", nativeQuery = true)
    Optional<PostLikeNotification> findByTriggeringUserIdAndPostId(Long triggeringUserId, Long postId);
}
