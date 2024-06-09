package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.*;
import de.tinf22b6.dhbwhub.model.notification_tables.*;
import de.tinf22b6.dhbwhub.proposal.simplified_models.HomepageNotificationProposal;

public class NotificationMapper {
    public static String POST_URL = "/post/?id=";
    public static String EVENT_URL = "/event/?id=";
    public static String USER_URL = "/user/?id=";
    public static String FRIENDS_URL = "/friends";

    public static HomepageNotificationProposal mapToSingleNotification(PostLikeNotification notification) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your post '%s' has been liked by the user '%s'.", notification.getPost().getTitle(), notification.getTriggeringUser().getAccount().getUsername()),
                POST_URL + notification.getPostId(),
                NotificationType.TYPE_POST_LIKE.getType()
        );
    }

    public static HomepageNotificationProposal mapToSingleNotification(PostCommentNotification notification) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your post '%s' has been commented by the user '%s'.", notification.getPost().getTitle(), notification.getTriggeringUser().getAccount().getUsername()),
                POST_URL + notification.getPostId(),
                NotificationType.TYPE_POST_COMMENT.getType()
        );
    }

    public static HomepageNotificationProposal mapToSingleNotification(FollowNotification notification) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("The user '%s' follows you now.", notification.getTriggeringUser().getAccount().getUsername()),
                USER_URL + notification.getTriggeringUser().getId(),
                NotificationType.TYPE_FOLLOW.getType()
        );
    }

    public static HomepageNotificationProposal mapToSingleNotification(CommentLikeNotification notification) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your comment on the post '%s' has been liked by the user '%s'.", notification.getPost().getTitle(), notification.getTriggeringUser().getAccount().getUsername()),
                POST_URL + notification.getPostId(),
                NotificationType.TYPE_COMMENT_LIKE.getType()
        );
    }

    public static HomepageNotificationProposal mapToSingleNotification(EventCommentLikeNotification notification) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your comment on the event '%s' has been liked by the user '%s'.", notification.getEventPost().getTitle(), notification.getTriggeringUser().getAccount().getUsername()),
                EVENT_URL + notification.getEventPostId(),
                NotificationType.TYPE_EVENT_COMMENT_LIKE.getType()
        );
    }

    public static HomepageNotificationProposal mapToGroupNotification(PostLikeNotification notification, int size) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your post '%s' has been liked by %d users.", notification.getPost().getTitle(), size),
                POST_URL + notification.getPostId(),
                NotificationType.TYPE_POST_LIKE.getType()
        );
    }

    public static HomepageNotificationProposal mapToGroupNotification(PostCommentNotification notification, int size) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your post '%s' has been commented by %d users.", notification.getPost().getTitle(), size),
                POST_URL + notification.getPostId(),
                NotificationType.TYPE_POST_COMMENT.getType()
        );
    }

    public static HomepageNotificationProposal mapToGroupNotification(FollowNotification notification, int size) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("You have %d new follower now.", size),
                FRIENDS_URL,
                NotificationType.TYPE_FOLLOW.getType()
        );
    }

    public static HomepageNotificationProposal mapToGroupNotification(CommentLikeNotification notification, int size) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your comment on the post '%s' has been liked by %d users", notification.getPost().getTitle(), size),
                POST_URL + notification.getPostId(),
                NotificationType.TYPE_COMMENT_LIKE.getType()
        );
    }

    public static HomepageNotificationProposal mapToGroupNotification(EventCommentLikeNotification notification, int size) {
        return new HomepageNotificationProposal(
                notification.getId(),
                notification.getAccumulatedId(),
                String.format("Your comment on the event '%s' has been liked by %d users.", notification.getEventPost().getTitle(), size),
                EVENT_URL + notification.getEventPostId(),
                NotificationType.TYPE_EVENT_COMMENT_LIKE.getType()
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

    public static EventCommentLikeNotification mapToEventCommentLikeNotification(EventComment eventComment, User user) {
        return new EventCommentLikeNotification(
                eventComment.getUser(),
                eventComment.getEventPost(),
                user,
                false
        );
    }
}
