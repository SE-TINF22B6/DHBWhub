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

    @PutMapping("/post-increase-likes/{id}")
    public int increaseLikesPost(@PathVariable Long id) {
        return service.increaseLikes(id,0);
    }

    @PutMapping("/post-decrease-likes/{id}")
    public int decreaseLikesPost(@PathVariable Long id) {
        return service.decreaseLikes(id,0);
    }

    @PutMapping("/comment-increase-likes/{id}")
    public int increaseLikesComment(@PathVariable Long id) {
        return service.increaseLikes(id,1);
    }

    @PutMapping("/comment-decrease-likes/{id}")
    public int decreaseLikesComment(@PathVariable Long id) {
        return service.decreaseLikes(id,1);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deletePost(id);
    }
}
