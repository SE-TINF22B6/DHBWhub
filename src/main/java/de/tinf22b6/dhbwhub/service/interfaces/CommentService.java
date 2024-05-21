package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.CommentThreadViewProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.CreateCommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.LikeCommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.UpdateCommentProposal;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
    Comment create(CommentProposal proposal);
    CommentThreadViewProposal create(CreateCommentProposal proposal);
    Comment get(Long id);
    Comment update(Long id, CommentProposal proposal);
    CommentThreadViewProposal update(Long id, UpdateCommentProposal proposal);
    void delete(Long id);
    List<Comment> findByPostId(Long postId);
    int increaseLikes(LikeCommentProposal likeCommentProposal);
    int decreaseLikes(LikeCommentProposal likeCommentProposal);
    CommentThreadViewProposal getCommentThreadView(Long id);
}
