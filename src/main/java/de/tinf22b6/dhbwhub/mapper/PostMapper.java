package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;

public class PostMapper {
    public static Post mapToModel(PostProposal proposal) {
        return new Post(
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getTimestamp(),
                proposal.getPicture() != null ? PictureMapper.mapToModel(proposal.getPicture()) : null,
                proposal.getUser() != null ? UserMapper.mapToModel(proposal.getUser()) : null,
                proposal.getCourse() != null ? CourseMapper.mapToModel(proposal.getCourse()) : null
        );
    }
}
