package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.model.SavedPost;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.SavedPostProposal;

import java.util.List;

public interface SavedPostService {
    List<SavedPost> getAll();
    SavedPost create(SavedPostProposal proposal);
    SavedPost get(Long id);
    void delete(Long id);
}
