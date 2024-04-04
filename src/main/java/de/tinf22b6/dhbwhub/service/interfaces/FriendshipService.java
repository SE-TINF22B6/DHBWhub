package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.proposal.FriendshipProposal;

import java.util.List;

public interface FriendshipService {
    List<Friendship> getAll();
    Friendship create(FriendshipProposal proposal);
    Friendship get(Long id);
    Friendship update(Long id, FriendshipProposal proposal);
    void delete(Long id);
}