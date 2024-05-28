package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.proposal.simplified_models.CreateSavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.DeleteSavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.HomepageSavedPostProposal;
import de.tinf22b6.dhbwhub.service.interfaces.SavedPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://www.dhbwhub.de", "http://localhost:3000"})
@RequestMapping(value = "/saved-post")
public class SavedPostController {
    private final SavedPostService service;

    public SavedPostController(@Autowired SavedPostService service) {
        this.service = service;
    }

    @GetMapping("/homepage-saved-posts/{id}")
    public List<HomepageSavedPostProposal> getSavedPosts(@PathVariable Long id) {
        return service.getSavedPostsByUserId(id);
    }

    @PostMapping
    public HomepageSavedPostProposal createSavedPost(@RequestBody CreateSavedPostProposal proposal) {
        return service.createSavedPost(proposal);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody DeleteSavedPostProposal proposal) {
        service.delete(proposal);
    }
}