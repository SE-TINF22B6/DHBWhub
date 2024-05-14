package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.mapper.FriendshipMapper;
import de.tinf22b6.dhbwhub.mapper.PostMapper;
import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.FollowUserProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.FriendlistProposal;
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

import static org.hamcrest.CoreMatchers.is;
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
    void GetFriendlist_StatusIsOk() throws Exception {
        FriendlistProposal friendlistProposal = createDefaultFriendlistProposal();

        when(friendshipService.getFriendlist(1L)).thenReturn(List.of(friendlistProposal, friendlistProposal));

        ResultActions response = mockMvc.perform(get("/friendship/friendlist/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void FollowUser_StatusIsOk() throws Exception {
        FriendlistProposal friendlistProposal = createDefaultFriendlistProposal();
        FollowUserProposal followUserProposal = createDefaultFollowUserProposal();
        given(friendshipService.followUser(any(FollowUserProposal.class))).willAnswer(i -> friendlistProposal);

        ResultActions response = mockMvc.perform(post("/friendship/follow-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(followUserProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(friendlistProposal.getUsername())))
        ;
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        doNothing().when(friendshipService).delete(any(Long.class));

        ResultActions response = mockMvc.perform(delete("/friendship/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }
}