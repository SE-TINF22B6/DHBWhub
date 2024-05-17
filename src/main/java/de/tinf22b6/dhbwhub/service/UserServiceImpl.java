package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.UserMapper;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.UserProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.UserLikes;
import de.tinf22b6.dhbwhub.repository.LogtableRepository;
import de.tinf22b6.dhbwhub.repository.UserRepository;
import de.tinf22b6.dhbwhub.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final LogtableRepository logtableRepository;

    public UserServiceImpl(@Autowired UserRepository repository,
                           @Autowired LogtableRepository logtableRepository) {
        this.repository = repository;
        this.logtableRepository = logtableRepository;
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserProposal proposal) {
        User user = UserMapper.mapToModel(proposal);
        return repository.save(user);
    }

    @Override
    public User get(Long id) {
        User user = repository.find(id);
        if (user == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", User.class.getSimpleName(), id));
        }
        return user;
    }

    @Override
    public User update(Long id, UserProposal proposal) {
        // Check if user exists
        get(id);

        User user = UserMapper.mapToModel(proposal);
        user.setId(id);
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        // Check if user exists
        get(id);

        repository.delete(id);
    }

    @Override
    public UserLikes getUserLikes(Long userId) {
        UserLikes userLikes = new UserLikes();
        userLikes.setUserId(userId);
        userLikes.setLikedPosts(logtableRepository.findPostsByUser(userId).stream().map(u -> u.getPost().getId()).toList());
        userLikes.setLikedComments(logtableRepository.findCommentsByUser(userId).stream().map(u -> u.getComment().getId()).toList());
        userLikes.setLikedEvents(logtableRepository.findEventPostsByUser(userId).stream().map(u -> u.getEventPost().getId()).toList());
        userLikes.setLikedEventComments(logtableRepository.findEventCommentsByUser(userId).stream().map(u -> u.getEventComment().getId()).toList());

        return userLikes;
    }
}
