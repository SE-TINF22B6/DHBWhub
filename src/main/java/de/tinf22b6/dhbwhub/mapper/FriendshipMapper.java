package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.*;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;

public class FriendshipMapper {
    public static FriendlistProposal mapToFriendlist(Friendship friendship){
        return new FriendlistProposal(
                friendship.getId(),
                friendship.getReceiver().getId(),
                friendship.getReceiver().getUsername(),
                friendship.getReceiver().getPicture() != null ? friendship.getReceiver().getPicture().getImageData() : null
                );
    }
    public static Friendship mapToFriendship(Account requester, Account receiver){
        return new Friendship(
                requester,
                receiver);
    }
}
