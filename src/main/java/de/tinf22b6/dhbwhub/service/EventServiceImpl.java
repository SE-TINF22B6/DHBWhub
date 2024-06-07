package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.EventMapper;
import de.tinf22b6.dhbwhub.mapper.NotificationMapper;
import de.tinf22b6.dhbwhub.model.EventComment;
import de.tinf22b6.dhbwhub.model.EventPost;
import de.tinf22b6.dhbwhub.model.EventTag;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtableEventComment;
import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtableEventPost;
import de.tinf22b6.dhbwhub.model.notification_tables.EventCommentLikeNotification;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;
import de.tinf22b6.dhbwhub.repository.EventRepository;
import de.tinf22b6.dhbwhub.repository.LogtableRepository;
import de.tinf22b6.dhbwhub.repository.NotificationRepository;
import de.tinf22b6.dhbwhub.repository.UserRepository;
import de.tinf22b6.dhbwhub.service.interfaces.EventService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository repository;
    private final UserRepository userRepository;
    private final LogtableRepository logtableRepository;
    private final NotificationRepository notificationRepository;

    public EventServiceImpl(@Autowired EventRepository repository,
                            @Autowired UserRepository userRepository,
                            @Autowired LogtableRepository logtableRepository,
                            @Autowired NotificationRepository notificationRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.logtableRepository = logtableRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public EventPost getEventPost(Long id) {
        EventPost eventPost = repository.findEventPost(id);
        if (eventPost == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", EventPost.class.getSimpleName(), id));
        }
        return eventPost;
    }

    @Override
    public EventComment getEventComment(Long id) {
        EventComment eventComment = repository.findEventComment(id);
        if (eventComment == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", EventComment.class.getSimpleName(), id));
        }
        return eventComment;
    }

    @Override
    public EventTag getEventTag(Long id) {
        EventTag eventTag = repository.findEventTag(id);
        if (eventTag == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", EventTag.class.getSimpleName(), id));
        }
        return eventTag;
    }

    @Override
    public List<HomepageEventPreviewProposal> getHomepageEvents() {
        List<HomepageEventPreviewProposal> homepageEventPreviewProposals = repository.findAllEventPosts().stream().filter(p -> p.getStartdate().after(new Date())).map(EventMapper::mapToHomepagePreviewProposal).limit(5).toList();
        homepageEventPreviewProposals.forEach(p -> p.setTags(getEventTags(p.getId())));
        return homepageEventPreviewProposals;
    }

    @Override
    public EventThreadViewProposal create(CreateEventPostProposal proposal) {
        EventPost post = repository.save(EventMapper.mapToModel(proposal));

        // Creating Tags after the Post is created
        Arrays.stream(proposal.getTags()).forEach(t -> {
            EventTag eventTag = new EventTag(post, t);
            repository.save(eventTag);
        } );

        return getEventThreadView(post.getId());
    }

    @Override
    public EventThreadViewProposal getEventThreadView(Long id) {
        EventThreadViewProposal eventThreadViewProposal = EventMapper.mapToPostThreadViewProposal(getEventPost(id));

        eventThreadViewProposal.setTags(getEventTags(id));
        eventThreadViewProposal.setCommentAmount(getAmountOfComments(id));
        eventThreadViewProposal.setComments(getEventComments(id));

        return eventThreadViewProposal;
    }

    @Override
    public int getAmountOfComments(Long id) {
        return repository.getAmountOfComments(id);
    }

    @Override
    public void deletePost(Long id) {
        getEventPost(id);
        repository.deleteEventPost(id);
    }

    @Override
    public void deleteComment(Long id) {
        getEventComment(id);
        repository.deleteEventComment(id);
    }

    @Override
    public void deleteTag(Long id) {
        getEventTag(id);
        repository.deleteEventTag(id);
    }

    @Override
    public EventCommentThreadViewProposal create(CreateEventCommentProposal proposal) {
        User user = userRepository.findByAccountId(proposal.getAccountId());
        EventPost post = repository.findEventPost(proposal.getEventId());
        EventComment comment = repository.save(EventMapper.mapToModel(proposal,user,post));
        return EventMapper.mapToThreadView(comment);
    }

    @Override
    public EventCommentThreadViewProposal update(Long id, UpdateEventCommentProposal proposal) {
        EventComment initialComment = getEventComment(id);
        EventComment updatedComment = EventMapper.mapToModel(proposal, initialComment);
        updatedComment.setId(id);

        repository.save(updatedComment);

        return EventMapper.mapToThreadView(getEventComment(id));    }

    @Override
    public EventCommentThreadViewProposal getEventCommentThreadView(Long id) {
        return EventMapper.mapToThreadView(getEventComment(id));
    }

    @Override
    public int adjustPostLikes(LikeEventPostProposal likeEventPostProposal, int i) {
        EventPost eventPost = getEventPost(likeEventPostProposal.getEventId());
        int likes;
        if(i == 0){
            likes = eventPost.getLikes() + 1;

            User user = userRepository.find(likeEventPostProposal.getUserId());
            LikeLogtableEventPost likeLogtableEventPost = new LikeLogtableEventPost(user, eventPost);
            if(logtableRepository.checkIfEventPostExists(eventPost.getId(), user.getId())){
                throw new EntityExistsException("Entity already exists!");
            }
            logtableRepository.saveEvent(likeLogtableEventPost);
        } else {
            likes = eventPost.getLikes() == 0 ? 0 : eventPost.getLikes() - 1;
            LikeLogtableEventPost likeLogtableEventPost = logtableRepository.findEventPost(likeEventPostProposal.getEventId(), likeEventPostProposal.getUserId());
            if(likeLogtableEventPost == null){
                throw new NoSuchEntryException("Entity does not exist");
            }
            logtableRepository.deleteEventPost(likeLogtableEventPost.getId());
        }
        EventPost updatedPost = EventMapper.mapToModel(eventPost,likes);
        updatedPost.setId(eventPost.getId());
        return repository.save(updatedPost).getLikes();
    }

    @Override
    public boolean isPostLiked(LikeEventPostProposal likeEventPostProposal) {
        return logtableRepository.findEventPost(likeEventPostProposal.getEventId(), likeEventPostProposal.getUserId()) != null;
    }

    @Override
    public int adjustCommentLikes(LikeEventCommentProposal likeEventCommentProposal, int i) {
        EventComment eventComment = getEventComment(likeEventCommentProposal.getEventCommentId());
        int likes;
        if(i == 0){
            likes = eventComment.getLikes() + 1;

            User user = userRepository.find(likeEventCommentProposal.getUserId());
            LikeLogtableEventComment likeLogtableEventComment = new LikeLogtableEventComment(user, eventComment);
            if(logtableRepository.checkIfEventCommentExists(eventComment.getId(), user.getId())){
                throw new EntityExistsException("Entity already exists!");
            }
            logtableRepository.saveEventComment(likeLogtableEventComment);

            if (!Objects.equals(eventComment.getUser().getId(), user.getId())) {
                EventCommentLikeNotification notification = NotificationMapper.mapToEventCommentLikeNotification(eventComment, user);
                notification.setAccumulatedId(null);
                notificationRepository.saveEventCommentLikeNotification(notification);
            }
        } else {
            likes = eventComment.getLikes() == 0 ? 0 : eventComment.getLikes() - 1;
            LikeLogtableEventComment likeLogtableEventComment = logtableRepository.findEventComment(likeEventCommentProposal.getEventCommentId(),likeEventCommentProposal.getUserId());
            if(likeLogtableEventComment == null){
                throw new NoSuchEntryException("Entity does not exist");
            }
            logtableRepository.deleteEventComment(likeLogtableEventComment.getId());
        }
        EventComment updatedComment = EventMapper.mapToModel(eventComment,likes);
        updatedComment.setId(eventComment.getId());
        return repository.save(updatedComment).getLikes();
    }

    @Override
    public boolean isCommentLiked(LikeEventCommentProposal likeEventCommentProposal) {
        return logtableRepository.findEventComment(likeEventCommentProposal.getEventCommentId(), likeEventCommentProposal.getUserId()) != null;
    }

    @Override
    public List<CalendarEventProposal> getCalendarEvents() {
        return this.repository.findAllEventPosts().stream().map(EventMapper::mapToCalendarEventProposal).toList();
    }

    @Override
    public List<EventCommentThreadViewProposal> getEventComments(Long id) {
        return repository.getEventComments(id);
    }

    @Override
    public List<String> getEventTags(Long id) {
        return repository.getEventTags(id);
    }

}
