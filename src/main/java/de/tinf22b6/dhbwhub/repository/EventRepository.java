package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.mapper.EventMapper;
import de.tinf22b6.dhbwhub.model.EventComment;
import de.tinf22b6.dhbwhub.model.EventPost;
import de.tinf22b6.dhbwhub.model.EventTag;
import de.tinf22b6.dhbwhub.proposal.simplified_models.EventCommentThreadViewProposal;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringEventCommentRepository;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringEventPostRepository;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringEventTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class EventRepository {
    private final SpringEventPostRepository eventPostRepository;
    private final SpringEventCommentRepository eventCommentRepository;
    private final SpringEventTagRepository eventTagRepository;

    public EventRepository(@Autowired SpringEventPostRepository eventPostRepository,
                           @Autowired SpringEventCommentRepository eventCommentRepository,
                           @Autowired SpringEventTagRepository eventTagRepository) {
        this.eventPostRepository  = eventPostRepository;
        this.eventCommentRepository = eventCommentRepository;
        this.eventTagRepository = eventTagRepository;
    }

    public EventPost save(EventPost eventPost) {
        return eventPostRepository.save(eventPost);
    }

    public EventTag save(EventTag eventTag) {
        return eventTagRepository.save(eventTag);
    }

    public EventComment save(EventComment eventComment) {
        return eventCommentRepository.save(eventComment);
    }
    public List<EventPost> findAllEventPosts() {
        return eventPostRepository.findAll();
    }

    public EventPost findEventPost(Long id) {
        return eventPostRepository.findById(id).orElse(null);
    }


    public void deleteEventPost(Long id) {
        eventPostRepository.deleteById(id);
    }

    public EventComment findEventComment(Long id) {
        return eventCommentRepository.findById(id).orElse(null);
    }

    public List<EventComment> findAllEventComments() {
        return eventCommentRepository.findAll();
    }

    public void deleteEventComment(Long id) {
        eventCommentRepository.deleteById(id);
    }

    public int getAmountOfComments(Long id){
        Integer commentAmount = eventPostRepository.getCommentAmount(id);
        return commentAmount != null ? commentAmount : 0;
    }

    public List<EventCommentThreadViewProposal> getEventComments(Long id) {
        List<EventComment> eventComments = eventCommentRepository.findByEventPostId(id);
        if (eventComments == null){
            return Collections.emptyList();
        }
        return eventComments.stream().map(EventMapper::mapToThreadView).toList();
    }

    public List<String> getEventTags(Long id) {
        List<EventTag> eventTags = eventTagRepository.findByEventPostId(id);
        if (eventTags == null){
            return Collections.emptyList();
        }
        return eventTags.stream().map(EventTag::getTag).toList();
    }

    public EventTag findEventTag(Long id) {
        return eventTagRepository.findById(id).orElse(null);
    }

    public void deleteEventTag(Long id) {
        eventTagRepository.deleteById(id);
    }

    public List<EventTag> findTagsByPostId(Long id) {
        return eventTagRepository.findByEventPostId(id);
    }
}
