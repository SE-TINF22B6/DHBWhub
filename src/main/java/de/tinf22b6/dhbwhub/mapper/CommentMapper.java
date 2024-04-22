package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.CommentThreadViewProposal;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommentMapper {
    public static Comment mapToModel(CommentProposal proposal) {
        return new Comment(
                proposal.getDescription(),
                proposal.getTimestamp(),
                proposal.getLikes(),
                proposal.getPicture() != null ? PictureMapper.mapToModel(proposal.getPicture()) : null,
                proposal.getUser() != null ? UserMapper.mapToModel(proposal.getUser()) : null,
                proposal.getPost() != null ? PostMapper.mapToModel(proposal.getPost()) : null
        );
    }

    public static CommentThreadViewProposal mapToThreadView(Comment comment) {
        return new CommentThreadViewProposal(
                comment.getPost() != null? comment.getPost().getId() : null,
                comment.getId(),
                comment.getUser() != null ? comment.getUser().getAccount().getId() : null,
                comment.getUser() != null ? comment.getUser().getAccount().getUsername() : null,
                comment.getUser() != null && comment.getUser().getAccount().getPicture() != null ? comment.getUser().getAccount().getPicture().getImageData() : null,
                comment.getDescription(),
                comment.getLikes(),
                (int) TimeUnit.MILLISECONDS.toDays(new Date().getTime() - comment.getTimestamp().getTime()),
                comment.getPicture() != null ? comment.getPicture().getImageData() : null
                );
    }
}
