package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.notification_tables.FollowNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringFollowNotificationRepository extends JpaRepository<FollowNotification, Long> {
    List<FollowNotification> findByAccumulatedId(Long accumulatedId);

    @Query(value = "SELECT * FROM follow_notification WHERE seen = false AND user_id = ?1", nativeQuery = true)
    List<FollowNotification> findUnseenNotifications(Long userId);

    @Query(value = "SELECT * FROM follow_notification WHERE triggering_user_id = ?1 AND user_id = ?2", nativeQuery = true)
    Optional<FollowNotification> findByTriggeringUserIdAndUserId(Long triggeringUserId, Long userId);
}
