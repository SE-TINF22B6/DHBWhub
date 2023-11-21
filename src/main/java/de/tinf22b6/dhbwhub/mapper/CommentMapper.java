package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;

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
}
