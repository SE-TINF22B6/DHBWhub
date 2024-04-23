package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringFriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendshipRepository {
    private final SpringFriendshipRepository repository;

    public FriendshipRepository(@Autowired SpringFriendshipRepository repository) {
        this.repository = repository;
    }

    public List<Friendship> findAll() {
        return repository.findAll();
    }

    public Friendship find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Friendship save(Friendship friendship) {
        return repository.save(friendship);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Friendship> getFriendlist(Long accountId) {
        return repository.findFriendlist(accountId);
    }

    public List<Friendship> getSentFriendrequests(Long accountId) {
        return repository.findSentRequests(accountId);
    }

    public List<Friendship> getReceivedFriendrequests(Long accountId) {
        return repository.findReceivedRequests(accountId);
    }

}