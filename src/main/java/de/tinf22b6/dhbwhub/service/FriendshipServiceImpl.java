package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.FriendshipMapper;
import de.tinf22b6.dhbwhub.mapper.NotificationMapper;
import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.model.notification_tables.FollowNotification;
import de.tinf22b6.dhbwhub.proposal.simplified_models.FollowUserProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.FriendlistProposal;
import de.tinf22b6.dhbwhub.repository.FriendshipRepository;
import de.tinf22b6.dhbwhub.repository.NotificationRepository;
import de.tinf22b6.dhbwhub.repository.UserRepository;
import de.tinf22b6.dhbwhub.service.interfaces.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {
    private final FriendshipRepository repository;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public FriendshipServiceImpl(@Autowired FriendshipRepository repository,
                                 @Autowired NotificationRepository notificationRepository,
                                 @Autowired UserRepository userRepository) {
        this.repository = repository;
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
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
    public List<FriendlistProposal> getFriendlist(Long id) {
        List<Friendship> friendlist = repository.getFriendlist(id);
        if (friendlist == null) {
            return Collections.emptyList();
        }
        return friendlist.stream().map(FriendshipMapper::mapToFriendlist).toList();
    }

    @Override
    public FriendlistProposal followUser(FollowUserProposal proposal) {
        User requester = userRepository.find(proposal.getRequesterId());
        if (requester == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", User.class.getSimpleName(), proposal.getRequesterId()));
        }

        User receiver = userRepository.find(proposal.getReceiverId());
        if (receiver == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", User.class.getSimpleName(), proposal.getReceiverId()));
        }

        if (repository.exists(proposal.getRequesterId(), proposal.getReceiverId()) != null) {
            throw new NoSuchEntryException(String.format("User already followed by User with id %s", proposal.getRequesterId()));
        }

        Friendship friendship = FriendshipMapper.mapToFriendship(requester, receiver);

        // notify receiver
        if (!notificationRepository.checkIfFollowEntryExists(requester.getId(), receiver.getId())) {
            FollowNotification followNotification = NotificationMapper.mapToFollowNotification(requester, receiver);
            followNotification.setAccumulatedId(null);
            notificationRepository.saveFollowNotification(followNotification);
        }

        return FriendshipMapper.mapToFriendlist(repository.save(friendship));
    }

    @Override
    public void unfollowUser(FollowUserProposal proposal) {
        User requester = userRepository.find(proposal.getRequesterId());
        if (requester == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", User.class.getSimpleName(), proposal.getRequesterId()));
        }

        User receiver = userRepository.find(proposal.getReceiverId());
        if (receiver == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", User.class.getSimpleName(), proposal.getReceiverId()));
        }

        Friendship friendship = repository.exists(proposal.getRequesterId(), proposal.getReceiverId());
        if (friendship == null) {
            throw new NoSuchEntryException(String.format("User doesn't follow User with id %s", proposal.getRequesterId()));
        }

        FollowNotification followNotification = notificationRepository.findFollowNotification(requester.getId(), receiver.getId());
        if (followNotification != null) {
            notificationRepository.deleteFollowNotification(followNotification);
        }

        repository.delete(friendship.getId());
    }

    @Override
    public boolean isFollowingUser(FollowUserProposal proposal) {
        User requester = userRepository.find(proposal.getRequesterId());
        if (requester == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", User.class.getSimpleName(), proposal.getRequesterId()));
        }

        User receiver = userRepository.find(proposal.getReceiverId());
        if (receiver == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", User.class.getSimpleName(), proposal.getReceiverId()));
        }

        return repository.exists(proposal.getRequesterId(), proposal.getReceiverId()) != null;
    }

    @Override
    public void delete(Long id) {
        // Check if account exists
        get(id);

        repository.delete(id);
    }
}
