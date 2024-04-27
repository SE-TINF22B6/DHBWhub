package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.*;

import java.util.List;

public interface PostService {
    List<Post> getAll();
    Post create(PostProposal proposal);
    PostThreadViewProposal create(CreatePostProposal proposal);
    Post get(Long id);
    Post update(Long id, PostProposal proposal);
    int increaseLikes(Long id);
    int decreaseLikes(Long id);
    PostThreadViewProposal update(Long id, UpdatePostProposal proposal);
    void delete(Long id);
    int getAmountOfComments(Long id);
    List<HomepagePostPreviewProposal> getHomepagePosts();
    List<HomepagePostPreviewProposal> getFacPosts(Long id);
    PostThreadViewProposal getPostThreadView(Long id);
    List<CommentThreadViewProposal> getPostComments(Long id);
    List<String> getPostTags(Long id);
}
