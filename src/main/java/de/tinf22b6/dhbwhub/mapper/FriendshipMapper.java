package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.proposal.FriendshipProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.FriendlistProposal;

import java.util.Objects;

public class FriendshipMapper {

    public static Friendship mapToModel(FriendshipProposal proposal){
        return new Friendship(
                AccountMapper.mapToModel(proposal.getRequester()),
                AccountMapper.mapToModel(proposal.getReceiver()),
                proposal.isAccepted()
        );
    }

    public static FriendlistProposal mapToFriendlist(Friendship friendship, Long id){
        Account account = Objects.equals(friendship.getReceiver().getId(), id) ? friendship.getRequester() : friendship.getReceiver();

        return new FriendlistProposal(
                friendship.getId(),
                account.getId(),
                account.getUsername(),
                account.getPicture() != null ? account.getPicture().getImageData() : null
                );
    }
}
