package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
@ComponentScan(basePackages = "de.tinf22b6.dhbwhub.repository")
class NotificationRepositoryTests extends AbstractApplicationTest {
    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void GetAllCommentLikeNotifications_HasSize_One() {
        notificationRepository.saveCommentLikeNotification(createCommentLikeNotification());
        assertThat(notificationRepository.getAllCommentLikeNotifications()).hasSize(1);
    }

    @Test
    void GetAllEventCommentLikeNotifications_HasSize_One() {
        notificationRepository.saveEventCommentLikeNotification(createEventCommentLikeNotification());
        assertThat(notificationRepository.getAllEventCommentLikeNotifications()).hasSize(1);
    }

    @Test
    void GetAllFollowNotifications_HasSize_One() {
        notificationRepository.saveFollowNotification(createFollowNotification());
        assertThat(notificationRepository.getAllFollowNotifications()).hasSize(1);
    }

    @Test
    void GetAllPostCommentNotifications_HasSize_One() {
        notificationRepository.savePostCommentNotification(createPostCommentNotification());
        assertThat(notificationRepository.getAllPostCommentNotifications()).hasSize(1);
    }

    @Test
    void GetAllPostLikeNotifications_HasSize_One() {
        notificationRepository.savePostLikeNotification(createPostLikeNotification());
        assertThat(notificationRepository.getAllPostLikeNotifications()).hasSize(1);
    }


    @Test
    void FindCommentLikeNotificationsById_IsNull() {
        assertThat(notificationRepository.findCommentLikeNotificationById(1L)).isNull();
    }


    @Test
    void FindEventCommentLikeNotificationsById_IsNull() {
        assertThat(notificationRepository.findEventCommentLikeNotificationById(1L)).isNull();
    }

    @Test
    void FindPostCommentNotificationsById_IsNull() {
        assertThat(notificationRepository.findPostCommentNotificationById(1L)).isNull();
    }

    @Test
    void FindPostLikeNotificationsById_IsNull() {
        assertThat(notificationRepository.findPostLikeNotificationById(1L)).isNull();
    }

    @Test
    void FindFollowNotificationsById_IsNull() {
        assertThat(notificationRepository.findFollowNotificationById(1L)).isNull();
    }

    @Test
    void SaveCommentLikeNotification_SizeChange() {
        notificationRepository.saveCommentLikeNotification(createCommentLikeNotification());
        assertThat(notificationRepository.getAllCommentLikeNotifications()).hasSize(1);
    }

    @Test
    void SavePostCommentNotification_SizeChange() {
        notificationRepository.savePostCommentNotification(createPostCommentNotification());
        assertThat(notificationRepository.getAllPostCommentNotifications()).hasSize(1);
    }

    @Test
    void SavePostLikeNotification_SizeChange() {
        notificationRepository.savePostLikeNotification(createPostLikeNotification());
        assertThat(notificationRepository.getAllPostLikeNotifications()).hasSize(1);
    }

    @Test
    void SaveEventCommentLikeNotification_SizeChange() {
        notificationRepository.saveEventCommentLikeNotification(createEventCommentLikeNotification());
        assertThat(notificationRepository.getAllEventCommentLikeNotifications()).hasSize(1);
    }

    @Test
    void SaveFollowNotification_SizeChange() {
        notificationRepository.saveFollowNotification(createFollowNotification());
        assertThat(notificationRepository.getAllFollowNotifications()).hasSize(1);
    }
}