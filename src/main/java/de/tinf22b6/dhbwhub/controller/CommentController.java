package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.CommentThreadViewProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.CreateCommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.LikeCommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.UpdateCommentProposal;
import de.tinf22b6.dhbwhub.service.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://www.dhbwhub.de", "http://localhost:3000"})
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

    @PostMapping("/create-comment")
    public CommentThreadViewProposal create(@RequestBody CreateCommentProposal proposal) {
        return service.create(proposal);
    }

    @GetMapping("/{id}")
    public Comment get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/comment-thread/{id}")
    public CommentThreadViewProposal getThreadView(@PathVariable Long id) {
        return service.getCommentThreadView(id);
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable Long id, @RequestBody CommentProposal proposal) {
        return service.update(id, proposal);
    }

    @PutMapping("/update-comment/{id}")
    public CommentThreadViewProposal update(@PathVariable Long id, @RequestBody UpdateCommentProposal proposal) {
        return service.update(id, proposal);
    }

    @PutMapping("/increase-likes")
    public int increaseLikes(@RequestBody LikeCommentProposal likeCommentProposal) {
        return service.increaseLikes(likeCommentProposal);
    }

    @PutMapping("/decrease-likes")
    public int decreaseLikes(@RequestBody LikeCommentProposal likeCommentProposal) {
        return service.decreaseLikes(likeCommentProposal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }



}
