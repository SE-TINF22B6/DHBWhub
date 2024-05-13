package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.*;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;

public class EventMapper {
    public static EventPost mapToModel(CreateEventPostProposal proposal, User user, Picture picture){
        return new EventPost(
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getLocation().getLocation(),
                proposal.getLocation().getLatitude(),
                proposal.getLocation().getLongitude(),
                proposal.getTimestamp(),
                proposal.getStartdate(),
                proposal.getEnddate(),
                0,
                picture,
                user
        );
    }

    public static EventPost mapToModel(UpdateEventPostProposal proposal, EventPost post, Picture picture){
        return new EventPost(
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getLocation().getLocation(),
                proposal.getLocation().getLatitude(),
                proposal.getLocation().getLongitude(),
                post.getTimestamp(),
                proposal.getStartdate(),
                proposal.getEnddate(),
                post.getLikes(),
                picture,
                post.getUser()
        );
    }

    public static EventPost mapToModel(EventPost post, int likes) {
        return new EventPost(
                post.getTitle(),
                post.getDescription(),
                post.getLocation(),
                post.getLatitude(),
                post.getLongitude(),
                post.getTimestamp(),
                post.getStartdate(),
                post.getEnddate(),
                likes,
                post.getPicture(),
                post.getUser()
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
                post.getTimestamp(),
                post.getStartdate(),
                post.getEnddate(),
                post.getPicture() != null ? post.getPicture().getImageData() : null,
                post.getUser() != null ? post.getUser().getAccount().getId() : null,
                post.getUser() != null ? post.getUser().getAccount().getUsername(): null,
                post.getUser().getAccount().getPicture() != null ? post.getUser().getAccount().getPicture().getImageData() : null,
                null
        );
    }

    public static EventCommentThreadViewProposal mapToThreadView(EventComment comment) {
        return new EventCommentThreadViewProposal(
                comment.getEventPost() != null? comment.getEventPost().getId() : null,
                comment.getId(),
                comment.getDescription(),
                comment.getUser() != null ? comment.getUser().getAccount().getUsername() : null,
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

