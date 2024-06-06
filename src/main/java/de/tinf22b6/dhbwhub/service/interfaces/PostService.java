package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;

import java.util.List;

public interface PostService {
    List<Post> getAll();
    Post create(PostProposal proposal);
    HomepagePostPreviewProposal create(CreatePostProposal proposal);
    void report(ReportPostProposal proposal);
    Post get(Long id);
    Post update(Long id, PostProposal proposal);
    int increaseLikes(LikePostProposal likePostProposal);
    int decreaseLikes(LikePostProposal likePostProposal);
    PostThreadViewProposal update(Long id, UpdatePostProposal proposal);
    void delete(Long id);
    int getAmountOfComments(Long id);
    List<HomepagePostPreviewProposal> getHomepagePosts();
    List<HomepagePostPreviewProposal> getFacPosts(Long id);
    PostThreadViewProposal getPostThreadView(Long id);
    HomepagePostPreviewProposal getPostHomepageView(Long id);
    List<CommentThreadViewProposal> getPostComments(Long id);
    List<String> getPostTags(Long id);
    List<String> getPopularPostTags();
    List<HomepagePostPreviewProposal> getPostsFromUser(Long id);
    List<HomepagePostPreviewProposal> getPostsFromFriends(Long id);
    List<HomepagePostPreviewProposal> getPostsByTag(String tag);
    List<HomepagePostPreviewProposal> getPostsByKeyword(String keyword);
    List<HomepagePostPreviewProposal> getPostTagsByKeyword(String keyword);
    List<HomepagePostPreviewProposal> getPostsFromFriendsByTag(Long id, String tag);
}
