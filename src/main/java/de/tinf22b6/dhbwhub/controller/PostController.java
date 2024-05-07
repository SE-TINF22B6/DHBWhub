package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;
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

    @GetMapping("/homepage-preview-posts")
    public List<HomepagePostPreviewProposal> getPostPreviewsHomepage() {
        return service.getHomepagePosts();
    }

    @GetMapping("/homepage-preview-posts/{id}")
    public List<HomepagePostPreviewProposal> getPostPreviewsHomepage(@PathVariable Long id) {
        return service.getFacPosts(id);
    }

    @GetMapping("/post-thread/{id}")
    public PostThreadViewProposal getPostThreadView(@PathVariable Long id) {
        return service.getPostThreadView(id);
    }

    @PostMapping
    public Post create(@RequestBody PostProposal proposal) {
        return service.create(proposal);
    }

    @PostMapping("/create-post")
    public HomepagePostPreviewProposal create(@RequestBody CreatePostProposal proposal) {
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

    @PutMapping("/update-post/{id}")
    public PostThreadViewProposal update(@PathVariable Long id, @RequestBody UpdatePostProposal proposal) {
        return service.update(id, proposal);
    }

    @PutMapping("/increase-likes/{id}")
    public int increaseLikes(@PathVariable Long id) {
         return service.increaseLikes(id);
    }

    @PutMapping("/decrease-likes/{id}")
    public int decreaseLikes(@PathVariable Long id) {
        return service.decreaseLikes(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


}
