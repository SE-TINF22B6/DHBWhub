package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.FriendshipMapper;
import de.tinf22b6.dhbwhub.model.*;
import de.tinf22b6.dhbwhub.proposal.FriendshipProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;
import de.tinf22b6.dhbwhub.repository.AccountRepository;
import de.tinf22b6.dhbwhub.repository.FriendshipRepository;
import de.tinf22b6.dhbwhub.service.interfaces.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendshipRepository repository;
    private final AccountRepository accountRepository;

    public FriendshipServiceImpl(@Autowired FriendshipRepository repository, AccountRepository accountRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Friendship get(Long id) {
        Friendship friendship = repository.find(id);
        if (friendship == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", Friendship.class.getSimpleName(), id));
        }
        return friendship;
    }

    @Override
    public void delete(Long id) {
        // Check if account exists
        get(id);

        repository.delete(id);
    }

    @Override
    public List<FriendlistProposal> getFriendlist(Long id) {
        List<Friendship> friendlist = repository.getFriendlist(id);
        if (friendlist == null) {
            return Collections.emptyList();
        }
        return friendlist.stream().map(FriendshipMapper::mapToFriendlist).toList();
    }

    @Override
    public FriendlistProposal followUser(FollowUserProposal proposal) {
        Account requester = accountRepository.find(proposal.getRequesterId());
        if (requester == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", Account.class.getSimpleName(), proposal.getRequesterId()));
        }
        Account receiver = accountRepository.find(proposal.getReceiverId());
        if (receiver == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", Account.class.getSimpleName(), proposal.getReceiverId()));
        }
        if(repository.exists(proposal.getRequesterId(), proposal.getReceiverId()) != null){
            throw new NoSuchEntryException(String.format("User already followed by User with id %s", proposal.getRequesterId()));
        }

        Friendship friendship = FriendshipMapper.mapToFriendship(requester, receiver);
        return FriendshipMapper.mapToFriendlist(repository.save(friendship));
    }
}
