package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.HomepagePostPreviewProposal;

import java.util.List;

public interface PostService {
    List<Post> getAll();
    Post create(PostProposal proposal);
    Post get(Long id);
    Post update(Long id, PostProposal proposal);
    void delete(Long id);
    int getAmountOfComments(Long id);
    List<HomepagePostPreviewProposal> getHomepagePosts();
    List<Post> getFacPosts(int id);

}
