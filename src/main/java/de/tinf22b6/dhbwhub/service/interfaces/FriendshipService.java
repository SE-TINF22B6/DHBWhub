package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;

import java.util.List;

public interface FriendshipService {
    Friendship get(Long id);
    List<FriendlistProposal> getFriendlist(Long id);
    FriendlistProposal followUser(FollowUserProposal proposal);
    void unfollowUser(FollowUserProposal proposal);
    boolean isFollowingUser(FollowUserProposal proposal);
    void delete(Long id);
}
