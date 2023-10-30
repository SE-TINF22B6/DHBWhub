package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.UserProposal;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User create(UserProposal proposal);
    User get(Long id);
    User update(Long id, UserProposal proposal);
    void delete(Long id);
}
