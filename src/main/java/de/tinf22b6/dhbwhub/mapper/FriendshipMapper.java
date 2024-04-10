package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.proposal.FriendshipProposal;

public class FriendshipMapper {

    public static Friendship mapToModel(FriendshipProposal proposal){
        return new Friendship(
                AccountMapper.mapToModel(proposal.getRequester()),
                AccountMapper.mapToModel(proposal.getReceiver()),
                proposal.isAccepted()
        );
    }
}
