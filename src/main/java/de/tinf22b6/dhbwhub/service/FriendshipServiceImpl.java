package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.FriendshipMapper;
import de.tinf22b6.dhbwhub.model.*;
import de.tinf22b6.dhbwhub.proposal.FriendshipProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.*;
import de.tinf22b6.dhbwhub.repository.FriendshipRepository;
import de.tinf22b6.dhbwhub.service.interfaces.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendshipRepository repository;

    public FriendshipServiceImpl(@Autowired FriendshipRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Friendship> getAll() {
        return repository.findAll();
    }

    @Override
    public Friendship create(FriendshipProposal proposal) {
        Friendship friendship = FriendshipMapper.mapToModel(proposal);
        return repository.save(friendship);
    }

    @Override
    public Friendship get(Long id) {
        Friendship friendship = repository.find(id);
        if (friendship == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", Account.class.getSimpleName(), id));
        }
        return friendship;
    }

    @Override
    public Friendship update(Long id, FriendshipProposal proposal) {
        // Check if account exists
        get(id);

        Friendship friendship = FriendshipMapper.mapToModel(proposal);
        friendship.setId(id);
        return repository.save(friendship);
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
        return friendlist.stream().map(f -> FriendshipMapper.mapToFriendlist(f, id)).toList();
    }

    @Override
    public List<FriendrequestProposal> getFriendrequests(Long id) {
        List<FriendrequestProposal> friendrequests = new ArrayList<>();
        friendrequests.addAll(getSentFriendrequests(id));
        friendrequests.addAll(getReceivedFriendrequests(id));

        return friendrequests;
    }

    private List<FriendrequestProposal> getSentFriendrequests(Long id) {
        List<Friendship> friendlist = repository.getSentFriendrequests(id);
        if (friendlist == null) {
            return Collections.emptyList();
        }
        return friendlist.stream().map(f -> FriendshipMapper.mapToFriendrequest(f, id)).toList();
    }

    private List<FriendrequestProposal> getReceivedFriendrequests(Long id) {
        List<Friendship> friendlist = repository.getReceivedFriendrequests(id);
        if (friendlist == null) {
            return Collections.emptyList();
        }
        return friendlist.stream().map(f -> FriendshipMapper.mapToFriendrequest(f, id)).toList();
    }
}
