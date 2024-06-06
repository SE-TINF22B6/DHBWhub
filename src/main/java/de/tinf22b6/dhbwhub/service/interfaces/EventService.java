package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.EventComment;
import de.tinf22b6.dhbwhub.model.EventPost;
import de.tinf22b6.dhbwhub.model.EventTag;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;

import java.util.List;

public interface EventService {
    EventPost getEventPost(Long id);
    EventComment getEventComment(Long id);
    EventTag getEventTag(Long id);
    List<HomepageEventPreviewProposal> getHomepageEvents();
    EventThreadViewProposal create(CreateEventPostProposal proposal);
    EventThreadViewProposal getEventThreadView(Long id);
    void deletePost(Long id);
    void deleteComment(Long id);
    void deleteTag(Long id);
    List<EventCommentThreadViewProposal> getEventComments(Long id);
    List<String> getEventTags(Long id);
    int getAmountOfComments(Long id);
    EventCommentThreadViewProposal create(CreateEventCommentProposal proposal);
    EventCommentThreadViewProposal update(Long id, UpdateEventCommentProposal proposal);
    EventCommentThreadViewProposal getEventCommentThreadView(Long id);
    int adjustPostLikes(LikeEventPostProposal likeEventPostProposal, int i);
    boolean isPostLiked(LikeEventPostProposal likeEventPostProposal);
    int adjustCommentLikes(LikeEventCommentProposal likeEventCommentProposal, int i);
    boolean isCommentLiked(LikeEventCommentProposal likeEventCommentProposal);
    List<CalendarEventProposal> getCalendarEvents();
}
