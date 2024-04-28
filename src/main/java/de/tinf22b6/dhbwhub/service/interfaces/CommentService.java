package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.CommentThreadViewProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.CreateCommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.UpdateCommentProposal;

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
    int increaseLikes(Long id);
    int decreaseLikes(Long id);
}
