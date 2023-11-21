package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.SavedPost;
import de.tinf22b6.dhbwhub.proposal.SavedPostProposal;

public class SavedPostMapper {
    public static SavedPost mapToModel(SavedPostProposal proposal) {
        return new SavedPost(
                UserMapper.mapToModel(proposal.getUser()),
                PostMapper.mapToModel(proposal.getPost())
        );
    }
}
