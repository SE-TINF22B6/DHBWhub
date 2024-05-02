package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Event;
import de.tinf22b6.dhbwhub.model.EventComment;
import de.tinf22b6.dhbwhub.model.EventPost;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringEventCommentRepository;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringEventPostRepository;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository {
    private final SpringEventPostRepository eventPostRepository;
    private final SpringEventCommentRepository eventCommentRepository;

    public EventRepository(@Autowired SpringEventPostRepository eventPostRepository,
                           @Autowired SpringEventCommentRepository eventCommentRepository) {
        this.eventPostRepository  = eventPostRepository;
        this.eventCommentRepository = eventCommentRepository;
    }

    public List<EventPost> findAllEventPosts() {
        return eventPostRepository.findAll();
    }

    public EventPost findEventPost(Long id) {
        return eventPostRepository.findById(id).orElse(null);
    }

    public EventPost saveEventPost(EventPost eventPost) {
        return eventPostRepository.save(eventPost);
    }

    public void deleteEventPost(Long id) {
        eventPostRepository.deleteById(id);
    }

    public List<EventComment> findAllEventComments() {
        return eventCommentRepository.findAll();
    }

    public EventComment findEventComment(Long id) {
        return eventCommentRepository.findById(id).orElse(null);
    }

    public EventComment saveEventComment(EventComment eventComment) {
        return eventCommentRepository.save(eventComment);
    }

    public void deleteEventComment(Long id) {
        eventCommentRepository.deleteById(id);
    }

}
