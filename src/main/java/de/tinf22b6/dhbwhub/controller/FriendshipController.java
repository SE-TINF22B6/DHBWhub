package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.proposal.FriendshipProposal;
import de.tinf22b6.dhbwhub.service.interfaces.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/friendship")
public class FriendshipController {
    private final FriendshipService service;

    public FriendshipController(@Autowired FriendshipService service) {
        this.service = service;
    }

    @GetMapping
    public List<Friendship> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Friendship create(@RequestBody FriendshipProposal proposal) {
        return service.create(proposal);
    }

    @GetMapping("/{id}")
    public Friendship get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Friendship update(@PathVariable Long id, @RequestBody FriendshipProposal proposal) {
        return service.update(id, proposal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}