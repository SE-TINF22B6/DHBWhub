package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.SavedPost;
import de.tinf22b6.dhbwhub.proposal.SavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.HomepageSavedPostProposal;

public class SavedPostMapper {
    public static SavedPost mapToModel(SavedPostProposal proposal) {
        return new SavedPost(
                UserMapper.mapToModel(proposal.getUser()),
                PostMapper.mapToModel(proposal.getPost())
        );
    }

    public static HomepageSavedPostProposal mapToHomepageProposal(SavedPost savedPost) {
        return new HomepageSavedPostProposal(
                savedPost.getId(),
                savedPost.getPost().getId(),
                savedPost.getPost().getTitle()
        );
    }
}
