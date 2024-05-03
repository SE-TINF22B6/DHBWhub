package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.CommentThreadViewProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.CreateCommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.UpdateCommentProposal;

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

    public static Comment mapToModel(CreateCommentProposal proposal, User user, Picture picture, Post post) {
        return new Comment(
                proposal.getDescription(),
                proposal.getTimestamp(),
                0,
                picture,
                user,
                post
        );
    }

    public static Comment mapToModel(UpdateCommentProposal proposal, Comment comment, Picture picture) {
        return new Comment(
                proposal.getDescription(),
                comment.getTimestamp(),
                comment.getLikes(),
                picture,
                comment.getUser(),
                comment.getPost()
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

    public static Comment mapToModel(Comment comment, int likes) {
        return new Comment(
                comment.getDescription(),
                comment.getTimestamp(),
                likes,
                comment.getPicture(),
                comment.getUser(),
                comment.getPost()
        );
    }
}
