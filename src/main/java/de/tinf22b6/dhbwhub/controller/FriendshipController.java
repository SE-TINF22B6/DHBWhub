package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.proposal.simplified_models.FollowUserProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.FriendlistProposal;
import de.tinf22b6.dhbwhub.service.interfaces.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://www.dhbwhub.de", "http://localhost:3000"})
@RequestMapping(value = "/friendship")
public class FriendshipController {
    private final FriendshipService service;

    public FriendshipController(@Autowired FriendshipService service) {
        this.service = service;
    }

    @GetMapping("/friendlist/{id}")
    public List<FriendlistProposal> getFriendlist(@PathVariable Long id) {
        return service.getFriendlist(id);
    }

    @PostMapping("/follow-user")
    public FriendlistProposal followUser(@RequestBody FollowUserProposal proposal) {
        return service.followUser(proposal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
