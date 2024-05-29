package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.*;
import de.tinf22b6.dhbwhub.model.notification_tables.*;
import de.tinf22b6.dhbwhub.proposal.simplified_models.HomepageNotificationProposal;

public class NotificationMapper {
    public static HomepageNotificationProposal mapToSingleNotification(PostLikeNotification notification) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your Post '%s' has been liked by the user %s", notification.getPost().getTitle(), notification.getTriggeringUser().getAccount().getUsername()),
                "post/post-thread/" + notification.getPostId(),
                NotificationType.TYPE_POST_LIKE.name()
        );
    }

    public static HomepageNotificationProposal mapToSingleNotification(PostCommentNotification notification) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your Post '%s' has been commented by the user %s", notification.getPost().getTitle(), notification.getTriggeringUser().getAccount().getUsername()),
                "post/post-thread/" + notification.getPostId(),
                NotificationType.TYPE_POST_COMMENT.name()
        );
    }

    public static HomepageNotificationProposal mapToSingleNotification(FollowNotification notification) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("The user '%s' follows you now", notification.getTriggeringUser().getAccount().getUsername()),
                "user/user-info/" + notification.getTriggeringUser().getId(),
                NotificationType.TYPE_FOLLOW.name()
        );
    }

    public static HomepageNotificationProposal mapToSingleNotification(CommentLikeNotification notification) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your comment on the post '%s' has been liked by the user %s", notification.getPost().getTitle(), notification.getTriggeringUser().getAccount().getUsername()),
                "post/post-thread/" + notification.getPostId(),
                NotificationType.TYPE_COMMENT_LIKE.name()
        );
    }

    public static HomepageNotificationProposal mapToSingleNotification(EventCommentLikeNotification notification) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your comment on the Event-Post '%s' has been liked by the user %s", notification.getEventPost().getTitle(), notification.getTriggeringUser().getAccount().getUsername()),
                "event/event-thread/" + notification.getEventPostId(),
                NotificationType.TYPE_EVENT_COMMENT_LIKE.name()
        );
    }

    public static HomepageNotificationProposal mapToGroupNotification(PostLikeNotification notification, int size) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your Post '%s' has been liked by %d users", notification.getPost().getTitle(), size),
                "post/post-thread/" + notification.getPostId(),
                NotificationType.TYPE_POST_LIKE.name()
        );
    }

    public static HomepageNotificationProposal mapToGroupNotification(PostCommentNotification notification, int size) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your Post '%s' has been commented by %d users", notification.getPost().getTitle(), size),
                "post/post-thread/" + notification.getPostId(),
                NotificationType.TYPE_POST_COMMENT.name()
        );
    }

    public static HomepageNotificationProposal mapToGroupNotification(FollowNotification notification, int size) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("You have %d new follower now", size),
                "friends",
                NotificationType.TYPE_FOLLOW.name()
        );
    }

    public static HomepageNotificationProposal mapToGroupNotification(CommentLikeNotification notification, int size) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your comment on the post '%s' has been liked by %d users", notification.getPost().getTitle(), size),
                "post/post-thread/" + notification.getPostId(),
                NotificationType.TYPE_COMMENT_LIKE.name()
        );
    }

    public static HomepageNotificationProposal mapToGroupNotification(EventCommentLikeNotification notification, int size) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your comment on the Event-Post '%s' has been liked by %d users", notification.getEventPost().getTitle(), size),
                "event/event-thread/" + notification.getEventPostId(),
                NotificationType.TYPE_EVENT_COMMENT_LIKE.name()
        );
    }

    public static PostLikeNotification mapToPostLikeNotification(Post post, User user) {
        return new PostLikeNotification(
                post.getUser(),
                post,
                user,
                false
        );
    }

    public static PostCommentNotification mapToPostCommentNotification(Comment comment, User user) {
        return new PostCommentNotification(
                comment.getPost().getUser(),
                comment.getPost(),
                user,
                false
        );
    }

    public static FollowNotification mapToFollowNotification(User requester, User receiver) {
        return new FollowNotification(
                receiver,
                requester,
                false
        );
    }

    public static CommentLikeNotification mapToCommentLikeNotification(Comment comment, User user) {
        return new CommentLikeNotification(
                comment.getPost().getUser(),
                comment.getPost(),
                user,
                false
        );
    }
}