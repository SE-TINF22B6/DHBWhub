package de.tinf22b6.dhbwhub.mapper.mapper;

import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.UserProposal;

public class UserMapper {
    public static User mapToModel(UserProposal proposal) {
        return new User(
                proposal.getAge(),
                proposal.getDescription(),
                CourseMapper.mapToModel(proposal.getCourse()),
                AccountMapper.mapToModel(proposal.getAccount())
        );
    }
}
