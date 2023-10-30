package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;

public class CommentMapper {
    public static Comment mapToModel(CommentProposal proposal) {
        return new Comment(
                proposal.getDescription(),
                proposal.getTimestamp(),
                PictureMapper.mapToModel(proposal.getPicture()),
                UserMapper.mapToModel(proposal.getUser()),
                PostMapper.mapToModel(proposal.getPost())
        );
    }
}
