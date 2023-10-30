package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.UserProposal;
import de.tinf22b6.dhbwhub.repository.UserRepository;
import de.tinf22b6.dhbwhub.service.interfaces.UserService;
import de.tinf22b6.dhbwhub.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(@Autowired UserRepository repository) {
        this.repository = repository;
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
            throw new NoSuchEntryException(User.class.getSimpleName(), id);
        }
        return user;
    }

    @Override
    public User update(Long id, UserProposal proposal) {
        // Check if user exists
        get(id);

        User user = UserMapper.mapToModel(proposal);
        user.setUserId(id);
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        // Check if user exists
        get(id);

        repository.delete(id);
    }
}
