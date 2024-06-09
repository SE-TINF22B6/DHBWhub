package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.simplified_models.FriendlistProposal;

public class FriendshipMapper {
    public static FriendlistProposal mapToFriendlist(Friendship friendship){
        return new FriendlistProposal(
                friendship.getId(),
                friendship.getReceiver().getId(),
                friendship.getReceiver().getAccount().getUsername(),
                friendship.getReceiver().getAccount().getPicture() != null ? friendship.getReceiver().getAccount().getPicture().getImageData() : null
                );
    }
    public static Friendship mapToFriendship(User requester, User receiver){
        return new Friendship(
                requester,
                receiver);
    }
}