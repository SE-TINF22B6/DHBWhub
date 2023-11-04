package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
    Comment create(CommentProposal proposal);
    Comment get(Long id);
    Comment update(Long id, CommentProposal proposal);
    void delete(Long id);
}
