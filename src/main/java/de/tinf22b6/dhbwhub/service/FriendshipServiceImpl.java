package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.FriendshipMapper;
import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.proposal.FriendshipProposal;
import de.tinf22b6.dhbwhub.repository.FriendshipRepository;
import de.tinf22b6.dhbwhub.service.interfaces.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
