package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.SavedPost;
import de.tinf22b6.dhbwhub.proposal.SavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.CreateSavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.DeleteSavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.HomepageSavedPostProposal;

import java.util.List;

public interface SavedPostService {
    List<SavedPost> getAll();
    SavedPost get(Long id);
    List<HomepageSavedPostProposal> getSavedPostsByUserId(Long id);
    HomepageSavedPostProposal createSavedPost(CreateSavedPostProposal proposal);
    SavedPost create(SavedPostProposal proposal);
    void delete(DeleteSavedPostProposal savedPostProposal);
}
