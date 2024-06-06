package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Course;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.UserProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.ProfileInformationProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.UserInformationProposal;

public class UserMapper {
    public static User mapToModel(UserProposal proposal) {
        return new User(
                proposal.getAge(),
                proposal.getDescription(),
                proposal.getCourse() != null ? CourseMapper.mapToModel(proposal.getCourse()) : null,
                proposal.getAccount() != null ? AccountMapper.mapToModel(proposal.getAccount()) : null
        );
    }

    public static UserInformationProposal mapToUserInformationProposal(User user, int followerAmount) {
        return new UserInformationProposal(
                user.getId(),
                user.getAccount().getPicture(),
                followerAmount,
                user.getAge(),
                user.getDescription(),
                user.getCourse() != null ? user.getCourse().getName() : null
        );
    }

    public static ProfileInformationProposal mapToProfileInformationProposal(User user) {
        return new ProfileInformationProposal(
                user.getAge(),
                user.getAccount().getEmail(),
                user.getDescription(),
                user.getCourse() != null ? user.getCourse().getName() : null
        );
    }

    public static User mapToUpdatedAgeModel(User user, int age) {
        return new User(
                age,
                user.getDescription(),
                user.getCourse(),
                user.getAccount()
        );
    }

    public static User mapToUpdatedDescriptionModel(User user, String description) {
        return new User(
                user.getAge(),
                description,
                user.getCourse(),
                user.getAccount()
        );
    }

    public static User mapToUpdatedCourseModel(User user, Course course) {
        return new User(
                user.getAge(),
                user.getDescription(),
                course,
                user.getAccount()
        );
    }
}
