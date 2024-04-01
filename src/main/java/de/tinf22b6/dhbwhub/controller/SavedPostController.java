package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.model.SavedPost;
import de.tinf22b6.dhbwhub.proposal.SavedPostProposal;
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

    @GetMapping
    public List<SavedPost> getAll() {
        return service.getAll();
    }

    @PostMapping
    public SavedPost create(@RequestBody SavedPostProposal proposal) {
        return service.create(proposal);
    }

    @GetMapping("/{id}")
    public SavedPost get(@PathVariable Long id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
