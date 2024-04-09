package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.model.Event;
import de.tinf22b6.dhbwhub.proposal.EventProposal;
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

    @GetMapping
    public List<Event> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Event create(@RequestBody EventProposal proposal) {
        return service.create(proposal);
    }

    @GetMapping("/{id}")
    public Event get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @RequestBody EventProposal proposal) {
        return service.update(id, proposal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
