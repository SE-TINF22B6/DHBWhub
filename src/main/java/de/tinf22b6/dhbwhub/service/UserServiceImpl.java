package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.AccountMapper;
import de.tinf22b6.dhbwhub.mapper.PictureMapper;
import de.tinf22b6.dhbwhub.mapper.UserMapper;
import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.Course;
import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.UserProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;
import de.tinf22b6.dhbwhub.repository.*;
import de.tinf22b6.dhbwhub.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final LogtableRepository logtableRepository;
    private final CourseRepository courseRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final PictureRepository pictureRepository;

    public UserServiceImpl(@Autowired UserRepository repository,
                           @Autowired AccountRepository accountRepository,
                           @Autowired LogtableRepository logtableRepository,
                           @Autowired CourseRepository courseRepository,
                           @Autowired PictureRepository pictureRepository,
                           @Autowired PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.accountRepository = accountRepository;
        this.logtableRepository = logtableRepository;
        this.courseRepository = courseRepository;
        this.pictureRepository = pictureRepository;
        this.passwordEncoder = passwordEncoder;
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
    public ProfileInformationProposal getProfileInformation(Long id) {
        User user = get(id);
        return UserMapper.mapToProfileInformationProposal(user);
    }

    @Override
    public UserInformationProposal getUserInformation(Long id) {
        User user = get(id);
        int followerAmount = repository.findAmountFollower(id);
        return UserMapper.mapToUserInformationProposal(user, followerAmount);
    }

    @Override
    public void updateAge(UpdateAgeProposal updateAgeProposal) {
        User user = get(updateAgeProposal.getUserId());
        User updatedUser = UserMapper.mapToUpdatedAgeModel(user, updateAgeProposal.getAge());
        updatedUser.setId(user.getId());
        repository.save(updatedUser);
    }

    @Override
    public void updateDescription(UpdateDescriptionProposal updateDescriptionProposal) {
        User user = get(updateDescriptionProposal.getUserId());
        User updatedUser = UserMapper.mapToUpdatedDescriptionModel(user, updateDescriptionProposal.getDescription());
        updatedUser.setId(user.getId());
        repository.save(updatedUser);
    }

    @Override
    public void updateCourse(UpdateCourseProposal updateCourseProposal) {
        User user = get(updateCourseProposal.getUserId());
        Course updatedCourse = courseRepository.findByName(updateCourseProposal.getCourse());
        if (updatedCourse == null) {
            throw new NoSuchEntryException("Course with name " + updateCourseProposal.getCourse() + " does not exist");
        }
        User updatedUser = UserMapper.mapToUpdatedCourseModel(user, updatedCourse);
        updatedUser.setId(user.getId());
        repository.save(updatedUser);
    }

    @Override
    public void updateEmail(UpdateEmailProposal proposal) {
        User user = get(proposal.getUserId());
        Account updatedAccount = AccountMapper.mapToUpdatedEmailProposal(user.getAccount(), proposal.getEmail());
        updatedAccount.setId(user.getAccount().getId());
        accountRepository.save(updatedAccount);
    }

    @Override
    public void updateUsername(UpdateUsernameProposal proposal) {
        User user = get(proposal.getUserId());
        Account updatedAccount = AccountMapper.mapToUpdatedUsernameProposal(user.getAccount(), proposal.getUsername());
        updatedAccount.setId(user.getAccount().getId());
        accountRepository.save(updatedAccount);
    }

    @Override
    public void updatePassword(UpdatePasswordProposal proposal) {
        User user = get(proposal.getUserId());
        String updatedPassword = passwordEncoder.encode(proposal.getNewPassword());
        Account updatedAccount = AccountMapper.mapToUpdatedPasswordProposal(user.getAccount(), updatedPassword);
        updatedAccount.setId(user.getAccount().getId());
        accountRepository.save(updatedAccount);
    }

    @Override
    public void updatePicture(UpdatePictureProposal proposal) {
        User user = get(proposal.getUserId());
        if (proposal.getImageData().length == 0){
            throw new IllegalArgumentException("Image data is empty");
        }
        Picture picture = pictureRepository.save(PictureMapper.mapToModelUser(proposal.getImageData()));
        Account updatedAccount = AccountMapper.mapToUpdatedPictureProposal(user.getAccount(), picture);
        updatedAccount.setId(user.getAccount().getId());

        accountRepository.save(updatedAccount);
    }

    @Override
    public boolean checkPasswordCorrectness(CheckPasswordCorrectnessProposal proposal) {
        User user = get(proposal.getUserId());
        String userPassword = user.getAccount().getPassword();
        String proposalPassword = proposal.getOldPassword();
        return passwordEncoder.matches(proposalPassword, userPassword);
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
