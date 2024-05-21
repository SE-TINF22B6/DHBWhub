package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.proposal.simplified_models.*;
import de.tinf22b6.dhbwhub.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/event")
public class EventController {
    private final EventService service;

    public EventController(@Autowired EventService service) {
        this.service = service;
    }

    @GetMapping("/homepage-preview-events")
    public List<HomepageEventPreviewProposal> getEventPreviewsHomepage() {
        return service.getHomepageEvents();
    }

    @GetMapping("/event-thread/{id}")
    public EventThreadViewProposal getEventThreadView(@PathVariable Long id) {
        return service.getEventThreadView(id);
    }

    @GetMapping("/event-comments/{id}")
    public List<EventCommentThreadViewProposal> getEventComments(@PathVariable Long id) {
        return service.getEventComments(id);
    }

    @GetMapping("/event-comment/{id}")
    public EventCommentThreadViewProposal getCommentThreadView(@PathVariable Long id) {
        return service.getEventCommentThreadView(id);
    }

    @PostMapping("/create-event")
    public EventThreadViewProposal create(@RequestBody CreateEventPostProposal proposal) {
        return service.create(proposal);
    }

    @PutMapping("/update-event/{id}")
    public EventThreadViewProposal update(@PathVariable Long id, @RequestBody UpdateEventPostProposal proposal) {
        return service.update(id, proposal);
    }

    @PostMapping("/create-comment")
    public EventCommentThreadViewProposal create(@RequestBody CreateEventCommentProposal proposal) {
        return service.create(proposal);
    }

    @PutMapping("/update-comment/{id}")
    public EventCommentThreadViewProposal update(@PathVariable Long id, @RequestBody UpdateEventCommentProposal proposal) {
        return service.update(id, proposal);
    }

    @PutMapping("/post-increase-likes")
    public int increaseLikesPost(@RequestBody LikeEventPostProposal likeEventPostProposal) {
        return service.adjustPostLikes(likeEventPostProposal,0);
    }

    @PutMapping("/post-decrease-likes")
    public int decreaseLikesPost(@RequestBody LikeEventPostProposal likeEventPostProposal) {
        return service.adjustPostLikes(likeEventPostProposal,1);
    }

    @PutMapping("/comment-increase-likes")
    public int increaseLikesComment(@RequestBody LikeEventCommentProposal likeEventCommentProposal) {
        return service.adjustCommentLikes(likeEventCommentProposal,0);
    }

    @PutMapping("/comment-decrease-likes")
    public int decreaseLikesComment(@RequestBody LikeEventCommentProposal likeEventCommentProposal) {
        return service.adjustCommentLikes(likeEventCommentProposal,1);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long id) {
        service.deletePost(id);
    }

    @DeleteMapping("/comment/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id) {
        service.deleteComment(id);
    }
}
