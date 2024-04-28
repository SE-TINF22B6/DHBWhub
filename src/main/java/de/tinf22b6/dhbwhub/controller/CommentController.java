package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.CommentThreadViewProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.CreateCommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.UpdateCommentProposal;
import de.tinf22b6.dhbwhub.service.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    private final CommentService service;

    public CommentController(@Autowired CommentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Comment> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Comment create(@RequestBody CommentProposal proposal) {
        return service.create(proposal);
    }

    @PostMapping("/createComment")
    public CommentThreadViewProposal create(@RequestBody CreateCommentProposal proposal) {
        return service.create(proposal);
    }

    @GetMapping("/{id}")
    public Comment get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable Long id, @RequestBody CommentProposal proposal) {
        return service.update(id, proposal);
    }

    @PutMapping("/updateComment/{id}")
    public CommentThreadViewProposal update(@PathVariable Long id, @RequestBody UpdateCommentProposal proposal) {
        return service.update(id, proposal);
    }

    @PutMapping("/increaseLikes/{id}")
    public int increaseLikes(@PathVariable Long id) {
        return service.increaseLikes(id);
    }

    @PutMapping("/decreaseLikes/{id}")
    public int decreaseLikes(@PathVariable Long id) {
        return service.decreaseLikes(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }



}
