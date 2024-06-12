package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.proposal.simplified_models.FollowUserProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.FriendlistProposal;
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

    @GetMapping("/friendlist/{id}")
    public List<FriendlistProposal> getFriendlist(@PathVariable Long id) {
        return service.getFriendlist(id);
    }

    @PostMapping("/follow-user")
    public FriendlistProposal followUser(@RequestBody FollowUserProposal proposal) {
        return service.followUser(proposal);
    }

    @PostMapping("/unfollow-user")
    public void unfollowUser(@RequestBody FollowUserProposal proposal) {
        service.unfollowUser(proposal);
    }

    @PostMapping("/is-following-user")
    public boolean isFollowingUser(@RequestBody FollowUserProposal proposal) {
        return service.isFollowingUser(proposal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
