package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.mapper.UserMapper;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.UserProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;
import de.tinf22b6.dhbwhub.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserControllerTests extends AbstractApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GetAll_StatusIsOk() throws Exception {
        User user = createDefaultUser();
        when(userService.getAll()).thenReturn(List.of(user, user));

        ResultActions response = mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void Get_StatusIsOk() throws Exception {
        User user = createDefaultUser();
        when(userService.get(any(Long.class))).thenReturn(user);

        ResultActions response = mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(user.getDescription())));
    }

    @Test
    void Create_StatusIsOk() throws Exception {
        UserProposal userProposal = createDefaultUserProposal();
        given(userService.create(any(UserProposal.class))).willAnswer(i -> UserMapper.mapToModel(i.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(userProposal.getDescription())));
    }

    @Test
    void Update_StatusIsOk() throws Exception {
        UserProposal userProposal = createDefaultUserProposal();
        when(userService.update(any(Long.class), any(UserProposal.class))).thenReturn(UserMapper.mapToModel(userProposal));

        ResultActions response = mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(userProposal.getDescription())));
    }

    @Test
    void GetLikedComponents_StatusIsOk() throws Exception {
        UserLikes userLikes = createDefaultUserLikes();
        when(userService.getUserLikes(1L)).thenReturn(userLikes);

        ResultActions response = mockMvc.perform(get("/user/liked-components/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)));
    }

    @Test
    void GetProfileInformation_StatusIsOk() throws Exception {
        ProfileInformationProposal profileInformationProposal = createProfileInformationProposal();
        when(userService.getProfileInformation(1L)).thenReturn(profileInformationProposal);

        ResultActions response = mockMvc.perform(get("/user/profile-information/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.age", is(profileInformationProposal.getAge())))
                .andExpect(jsonPath("$.email", is(profileInformationProposal.getEmail())))
                .andExpect(jsonPath("$.description", is(profileInformationProposal.getDescription())))
                .andExpect(jsonPath("$.course", is(profileInformationProposal.getCourse())));
    }

    @Test
    void GetUserInformation_StatusIsOk() throws Exception {
        UserInformationProposal userInformationProposal = createUserInformationProposal();
        when(userService.getUserInformation(1L)).thenReturn(userInformationProposal);

        ResultActions response = mockMvc.perform(get("/user/user-information/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.amountFollower", is(userInformationProposal.getAmountFollower())))
                .andExpect(jsonPath("$.age", is(userInformationProposal.getAge())))
                .andExpect(jsonPath("$.description", is(userInformationProposal.getDescription())))
                .andExpect(jsonPath("$.course", is(userInformationProposal.getCourse())));
    }

    @Test
    void UpdateAge_StatusIsOk() throws Exception {
        UpdateAgeProposal updateAgeProposal = createUpdateAgeProposal();
        doNothing().when(userService).updateAge(any(UpdateAgeProposal.class));

        ResultActions response = mockMvc.perform(put("/user/update-age")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateAgeProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void UpdateDescription_StatusIsOk() throws Exception {
        UpdateDescriptionProposal updateDescriptionProposal = createUpdateDescriptionProposal();
        doNothing().when(userService).updateDescription(any(UpdateDescriptionProposal.class));

        ResultActions response = mockMvc.perform(put("/user/update-description")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDescriptionProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void UpdateCourse_StatusIsOk() throws Exception {
        UpdateCourseProposal updateCourseProposal = createUpdateCourseProposal();
        doNothing().when(userService).updateCourse(any(UpdateCourseProposal.class));

        ResultActions response = mockMvc.perform(put("/user/update-course")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateCourseProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void UpdateEmail_StatusIsOk() throws Exception {
        UpdateEmailProposal updateEmailProposal = createUpdateEmailProposal();
        doNothing().when(userService).updateEmail(any(UpdateEmailProposal.class));

        ResultActions response = mockMvc.perform(put("/user/update-email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateEmailProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void UpdateUsername_StatusIsOk() throws Exception {
        UpdateUsernameProposal updateUsernameProposal = createUpdateUsernameProposal();
        doNothing().when(userService).updateUsername(any(UpdateUsernameProposal.class));

        ResultActions response = mockMvc.perform(put("/user/update-username")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateUsernameProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void UpdatePassword_StatusIsOk() throws Exception {
        UpdatePasswordProposal updatePasswordProposal = createUpdatePasswordProposal();
        doNothing().when(userService).updatePassword(any(UpdatePasswordProposal.class));

        ResultActions response = mockMvc.perform(put("/user/update-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatePasswordProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void UpdatePicture_StatusIsOk() throws Exception {
        UpdatePictureProposal updatePictureProposal = createUpdatePictureProposal();
        doNothing().when(userService).updatePicture(any(UpdatePictureProposal.class));

        ResultActions response = mockMvc.perform(put("/user/update-picture")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatePictureProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        doNothing().when(userService).delete(any(Long.class));

        ResultActions response = mockMvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }
}
