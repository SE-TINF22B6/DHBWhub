package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.*;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;

public class EventMapper {
    public static EventPost mapToModel(CreateEventPostProposal proposal){
        return new EventPost(
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getLocation().getLocation(),
                proposal.getLocation().getLatitude(),
                proposal.getLocation().getLongitude(),
                proposal.getStartdate(),
                proposal.getEnddate(),
                0
        );
    }

    public static EventPost mapToModel(UpdateEventPostProposal proposal, EventPost post){
        return new EventPost(
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getLocation().getLocation(),
                proposal.getLocation().getLatitude(),
                proposal.getLocation().getLongitude(),
                proposal.getStartdate(),
                proposal.getEnddate(),
                post.getLikes()
        );
    }

    public static EventPost mapToModel(EventPost post, int likes) {
        return new EventPost(
                post.getTitle(),
                post.getDescription(),
                post.getLocation(),
                post.getLatitude(),
                post.getLongitude(),
                post.getStartdate(),
                post.getEnddate(),
                likes
        );
    }

    public static EventComment mapToModel(EventComment comment, int likes) {
        return new EventComment(
                comment.getDescription(),
                comment.getTimestamp(),
                likes,
                comment.getUser(),
                comment.getEventPost()
        );
    }

    public static HomepageEventPreviewProposal mapToHomepagePreviewProposal(EventPost post) {
        return new HomepageEventPreviewProposal(
                post.getId(),
                post.getTitle(),
                post.getLocation(),
                null,
                post.getStartdate()
        );
    }

    public static EventThreadViewProposal mapToPostThreadViewProposal(EventPost post) {
        return new EventThreadViewProposal(
                post.getId(),
                post.getTitle(),
                post.getDescription(),
                null,
                new LocationProposal(post.getLocation(), post.getLatitude(), post.getLongitude()),
                post.getLikes(),
                0,
                post.getStartdate(),
                post.getEnddate(),
                null
        );
    }

    public static CalendarEventProposal mapToCalendarEventProposal(EventPost post) {
        return new CalendarEventProposal(
                post.getId(),
                post.getTitle(),
                post.getStartdate(),
                post.getEnddate()
        );
    }

    public static EventCommentThreadViewProposal mapToThreadView(EventComment comment) {
        return new EventCommentThreadViewProposal(
                comment.getEventPost() != null? comment.getEventPost().getId() : null,
                comment.getId(),
                comment.getDescription(),
                comment.getUser() != null ? comment.getUser().getAccount().getUsername() : User.USER_DELETED,
                comment.getUser() != null ? comment.getUser().getAccount().getId() : null,
                comment.getUser() != null && comment.getUser().getAccount().getPicture() != null ? comment.getUser().getAccount().getPicture().getImageData() : null,
                comment.getTimestamp(),
                comment.getLikes()
                );
    }

    public static EventComment mapToModel(CreateEventCommentProposal proposal, User user, EventPost post) {
        return new EventComment(
                proposal.getDescription(),
                proposal.getTimestamp(),
                0,
                user,
                post
        );
    }

    public static EventComment mapToModel(UpdateEventCommentProposal proposal, EventComment initialComment) {
        return new EventComment(
                proposal.getDescription(),
                initialComment.getTimestamp(),
                initialComment.getLikes(),
                initialComment.getUser(),
                initialComment.getEventPost()
        );
    }
}

