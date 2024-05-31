package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.UserProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;
import de.tinf22b6.dhbwhub.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://www.dhbwhub.de", "http://localhost:3000"})
@RequestMapping(value = "/user")
public class UserController {
    private final UserService service;

    public UserController(@Autowired UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @PostMapping
    public User create(@RequestBody UserProposal proposal) {
        return service.create(proposal);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/liked-components/{id}")
    public UserLikes getLikedComponents(@PathVariable Long id){
        return service.getUserLikes(id);
    }

    @GetMapping("/profile-information/{id}")
    public ProfileInformationProposal getProfileInformation(@PathVariable Long id) {
        return service.getProfileInformation(id);
    }

    @GetMapping("/user-information/{id}")
    public UserInformationProposal getUserInformation(@PathVariable Long id) {
        return service.getUserInformation(id);
    }

    @PutMapping("/update-age")
    @ResponseStatus(HttpStatus.OK)
    public void updateAge(@RequestBody UpdateAgeProposal proposal) {
        service.updateAge(proposal);
    }

    @PutMapping("/update-description")
    @ResponseStatus(HttpStatus.OK)
    public void updateDescription(@RequestBody UpdateDescriptionProposal proposal) {
        service.updateDescription(proposal);
    }

    @PutMapping("/update-course")
    @ResponseStatus(HttpStatus.OK)
    public void updateCourse(@RequestBody UpdateCourseProposal proposal) {
        service.updateCourse(proposal);
    }

    @PutMapping("/update-email")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmail(@RequestBody UpdateEmailProposal proposal) {
        service.updateEmail(proposal);
    }

    @PutMapping("/update-username")
    @ResponseStatus(HttpStatus.OK)
    public void updateUsername(@RequestBody UpdateUsernameProposal proposal) {
        service.updateUsername(proposal);
    }

    @PutMapping("/update-password")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@RequestBody UpdatePasswordProposal proposal) {
        service.updatePassword(proposal);
    }

    @PutMapping("/update-picture")
    @ResponseStatus(HttpStatus.OK)
    public void updatePicture(@RequestBody UpdatePictureProposal proposal) {
        service.updatePicture(proposal);
    }

    @PutMapping("/check-password-correctness")
    public ResponseEntity<?> checkPasswordCorrectness(@RequestBody CheckPasswordCorrectnessProposal proposal) {
        if(!service.checkPasswordCorrectness(proposal)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody UserProposal proposal) {
        return service.update(id, proposal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}