package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.model.SavedPost;
import de.tinf22b6.dhbwhub.proposal.SavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.CreateSavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.HomepageSavedPostProposal;
import de.tinf22b6.dhbwhub.service.interfaces.SavedPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/savedPost")
public class SavedPostController {
    private final SavedPostService service;

    public SavedPostController(@Autowired SavedPostService service) {
        this.service = service;
    }

    @GetMapping("/homepage-saved-posts/{id}")
    List<HomepageSavedPostProposal> getSavedPosts(@PathVariable Long id) {
        return service.getSavedPostsByUserId(id);
    }

    @PostMapping("/saved-post")
    HomepageSavedPostProposal createSavedPost(@RequestBody CreateSavedPostProposal proposal) {
        return service.createSavedPost(proposal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
