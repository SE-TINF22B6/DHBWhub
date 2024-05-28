package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.model.notification_tables.*;
import de.tinf22b6.dhbwhub.proposal.simplified_models.HomepageNotificationProposal;
import de.tinf22b6.dhbwhub.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class NotificationServiceImplTests extends AbstractApplicationTest {
    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Test
    void GetUserNotification_isNotNull(){
        Long userId = 1L;
        List<HomepageNotificationProposal> notifications = createHomepageNotificationProposals();
        PostLikeNotification postLikeNotification = createPostLikeNotification();
        PostCommentNotification postCommentNotification = createPostCommentNotification();
        CommentLikeNotification commentLikeNotification = createCommentLikeNotification();
        EventCommentLikeNotification eventCommentLikeNotification = createEventCommentLikeNotification();
        FollowNotification followNotification = createFollowNotification();

        postLikeNotification.getPost().setId(1L);
        postCommentNotification.getPost().setId(1L);
        commentLikeNotification.getPost().setId(1L);
        eventCommentLikeNotification.getEventPost().setId(1L);
        followNotification.getUser().setId(1L);

        when(notificationRepository.findPostLikeNotificationsByUser(1L)).thenReturn(List.of(postLikeNotification));
        when(notificationRepository.findPostCommentNotificationsByUser(1L)).thenReturn(List.of(postCommentNotification));
        when(notificationRepository.findCommentLikeNotificationsByUser(1L)).thenReturn(List.of(commentLikeNotification));
        when(notificationRepository.findEventCommentLikeNotificationsByUser(1L)).thenReturn(List.of(eventCommentLikeNotification));
        when(notificationRepository.findFollowNotificationsByUser(1L)).thenReturn(List.of(followNotification));


        assertThat(notificationService.getUserNotifications(userId)).isNotNull();
        assertThat(notificationService.getUserNotifications(userId)).hasSize(5);
    }
}
