package de.tinf22b6.dhbwhub.mapper.mapper;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;

public class PostMapper {
    public static Post mapToModel(PostProposal proposal) {
        return new Post(
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getTimestamp(),
                PictureMapper.mapToModel(proposal.getPicture()),
                UserMapper.mapToModel(proposal.getUser()),
                CourseMapper.mapToModel(proposal.getCourse())
        );
    }
}
