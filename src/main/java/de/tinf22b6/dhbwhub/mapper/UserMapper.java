package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.UserProposal;

public class UserMapper {
    public static User mapToModel(UserProposal proposal) {
        return new User(
                proposal.getAge(),
                proposal.getDescription(),
                proposal.getCourse() != null ? CourseMapper.mapToModel(proposal.getCourse()) : null,
                proposal.getAccount() != null ? AccountMapper.mapToModel(proposal.getAccount()) : null
        );
    }
}
