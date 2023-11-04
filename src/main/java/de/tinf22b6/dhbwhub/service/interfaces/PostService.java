package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;

import java.util.List;

public interface PostService {
    List<Post> getAll();
    Post create(PostProposal proposal);
    Post get(Long id);
    Post update(Long id, PostProposal proposal);
    void delete(Long id);
}
