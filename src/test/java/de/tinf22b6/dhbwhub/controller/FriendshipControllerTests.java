package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.mapper.FriendshipMapper;
import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.proposal.FriendshipProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.FriendlistProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.FriendrequestProposal;
import de.tinf22b6.dhbwhub.service.FriendshipServiceImpl;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = FriendshipController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class FriendshipControllerTests extends AbstractApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FriendshipServiceImpl friendshipService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GetAll_StatusIsOk() throws Exception {
        Friendship friendship = createDefaultFriendship();

        when(friendshipService.getAll()).thenReturn(List.of(friendship, friendship));

        ResultActions response = mockMvc.perform(get("/friendship")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void Get_StatusIsOk() throws Exception {
        Friendship friendship = createDefaultFriendship();

        when(friendshipService.get(any(Long.class))).thenReturn(friendship);

        ResultActions response = mockMvc.perform(get("/friendship/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

    @Test
    void GetFriendlist_StatusIsOk() throws Exception {
        FriendlistProposal friendlistProposal = createDefaultFriendlistProposal();

        when(friendshipService.getFriendlist(1L)).thenReturn(List.of(friendlistProposal, friendlistProposal));

        ResultActions response = mockMvc.perform(get("/friendship/friendlist/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void GetFriendrequests_StatusIsOk() throws Exception {
        FriendrequestProposal friendrequestProposal = createDefaultFriendrequestProposal();

        when(friendshipService.getFriendrequests(1L)).thenReturn(List.of(friendrequestProposal, friendrequestProposal));

        ResultActions response = mockMvc.perform(get("/friendship/friendrequests/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void Create_StatusIsOk() throws Exception {
        FriendshipProposal friendshipProposal = createDefaultFriendshipProposal();
        given(friendshipService.create(any(FriendshipProposal.class))).willAnswer(i -> FriendshipMapper.mapToModel(i.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/friendship")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(friendshipProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void Update_StatusIsOk() throws Exception {
        FriendshipProposal friendshipProposal = createDefaultFriendshipProposal();
        when(friendshipService.update(any(Long.class), any(FriendshipProposal.class))).thenReturn(FriendshipMapper.mapToModel(friendshipProposal));

        ResultActions response = mockMvc.perform(put("/friendship/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(friendshipProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        doNothing().when(friendshipService).delete(any(Long.class));

        ResultActions response = mockMvc.perform(delete("/friendship/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }
}