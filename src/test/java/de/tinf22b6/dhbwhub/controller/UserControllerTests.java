package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.mapper.UserMapper;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.UserProposal;
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
                .andExpect(jsonPath("$.age", is(user.getAge())))
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
                .andExpect(jsonPath("$.age", is(userProposal.getAge())))
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
                .andExpect(jsonPath("$.age", is(userProposal.getAge())))
                .andExpect(jsonPath("$.description", is(userProposal.getDescription())));
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        doNothing().when(userService).delete(any(Long.class));

        ResultActions response = mockMvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }
}
