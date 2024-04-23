package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.*;
import de.tinf22b6.dhbwhub.proposal.FriendshipProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.*;

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

    public static FriendrequestProposal mapToFriendrequest(Friendship friendship, Long id){
        Account account;
        String status;

        if(Objects.equals( friendship.getReceiver().getId(), id)){
            account = friendship.getRequester();
            status = "Received";
        } else {
            account = friendship.getReceiver();
            status = "Sent";
        }
        return new FriendrequestProposal(
                friendship.getId(),
                account.getId(),
                account.getUsername(),
                account.getPicture() != null ? account.getPicture().getImageData() : null,
                status
        );
    }
}
