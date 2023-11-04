package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.service.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {
    private final PostService service;

    public PostController(@Autowired PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Post create(@RequestBody PostProposal proposal) {
        return service.create(proposal);
    }

    @GetMapping("/{id}")
    public Post get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody PostProposal proposal) {
        return service.update(id, proposal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Long id) {
        service.delete(id);
    }
}
