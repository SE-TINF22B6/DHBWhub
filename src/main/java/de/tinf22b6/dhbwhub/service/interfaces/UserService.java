package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.UserProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User create(UserProposal proposal);
    User get(Long id);
    ProfileInformationProposal getProfileInformation(Long id);
    UserInformationProposal getUserInformation(Long id);
    void updateAge(UpdateAgeProposal updateAgeProposal);
    void updateDescription(UpdateDescriptionProposal updateDescriptionProposal);
    void updateCourse(UpdateCourseProposal updateCourseProposal);
    void updateEmail(UpdateEmailProposal proposal);
    void updateUsername(UpdateUsernameProposal proposal);
    void updatePassword(UpdatePasswordProposal proposal);
    void updatePicture(UpdatePictureProposal proposal);
    boolean checkPasswordCorrectness(CheckPasswordCorrectnessProposal proposal);
    User update(Long id, UserProposal proposal);
    void delete(Long id);
    UserLikes getUserLikes(Long userId);
}
