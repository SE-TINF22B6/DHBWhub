package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.mapper.FriendshipMapper;
import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.proposal.AccountProposal;
import de.tinf22b6.dhbwhub.proposal.FriendshipProposal;
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
public class FriendshipControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FriendshipServiceImpl friendshipService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GetAll_StatusIsOk() throws Exception {
        Account account = new Account("maxmustermann1234", "max@mustermann.de", "1234", null, false);

        Friendship friendship = new Friendship(account,account,false);

        when(friendshipService.getAll()).thenReturn(List.of(friendship, friendship));

        ResultActions response = mockMvc.perform(get("/friendship")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void Get_StatusIsOk() throws Exception {
        Account account = new Account("maxmustermann1234", "max@mustermann.de", "1234", null, false);
        Friendship friendship = new Friendship(account,account,false);

        when(friendshipService.get(any(Long.class))).thenReturn(friendship);

        ResultActions response = mockMvc.perform(get("/friendship/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

    @Test
    void Create_StatusIsOk() throws Exception {
        AccountProposal accountProposal = new AccountProposal("maxmustermann1234", "max@mustermann.de", "1234", null, false);
        FriendshipProposal friendshipProposal = new FriendshipProposal(accountProposal,accountProposal,false);
        given(friendshipService.create(any(FriendshipProposal.class))).willAnswer(i -> FriendshipMapper.mapToModel(i.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/friendship")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(friendshipProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void Update_StatusIsOk() throws Exception {
        AccountProposal accountProposal = new AccountProposal("maxmustermann1234", "max@mustermann.de", "1234", null, false);
        FriendshipProposal friendshipProposal = new FriendshipProposal(accountProposal,accountProposal,false);
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