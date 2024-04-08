package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.mapper.AccountMapper;
import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.proposal.AccountProposal;
import de.tinf22b6.dhbwhub.proposal.PictureProposal;
import de.tinf22b6.dhbwhub.service.AccountServiceImpl;
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

@WebMvcTest(controllers = AccountController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class AccountControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountServiceImpl accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GetAll_StatusIsOk() throws Exception {
        Picture picture = createPicture();
        Account account = new Account("maxmustermann1234", "max@mustermann.de", "1234", picture, false);
        when(accountService.getAll()).thenReturn(List.of(account, account));

        ResultActions response = mockMvc.perform(get("/account")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void Get_StatusIsOk() throws Exception {
        Picture picture = createPicture();
        Account account = new Account("maxmustermann1234", "max@mustermann.de", "1234", picture, false);

        when(accountService.get(any(Long.class))).thenReturn(account);

        ResultActions response = mockMvc.perform(get("/account/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(account.getUsername())))
                .andExpect(jsonPath("$.email", is(account.getEmail())))
                .andExpect(jsonPath("$.password", is(account.getPassword())));
    }

    @Test
    void Create_StatusIsOk() throws Exception {
        PictureProposal pictureProposal = createPictureProposal();
        AccountProposal accountProposal = new AccountProposal("maxmustermann1234", "max@mustermann.de", "1234", pictureProposal, false);

        given(accountService.create(any(AccountProposal.class))).willAnswer(i -> AccountMapper.mapToModel(i.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(accountProposal.getUsername())))
                .andExpect(jsonPath("$.email", is(accountProposal.getEmail())))
                .andExpect(jsonPath("$.password", is(accountProposal.getPassword())));
    }

    @Test
    void Update_StatusIsOk() throws Exception {
        PictureProposal pictureProposal = createPictureProposal();
        AccountProposal accountProposal = new AccountProposal("maxmustermann1234", "max@mustermann.de", "1234", pictureProposal, false);

        when(accountService.update(any(Long.class), any(AccountProposal.class))).thenReturn(AccountMapper.mapToModel(accountProposal));

        ResultActions response = mockMvc.perform(put("/account/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(accountProposal.getUsername())))
                .andExpect(jsonPath("$.email", is(accountProposal.getEmail())))
                .andExpect(jsonPath("$.password", is(accountProposal.getPassword())));
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        doNothing().when(accountService).delete(any(Long.class));

        ResultActions response = mockMvc.perform(delete("/account/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }

    private Picture createPicture() {
        return new Picture("profile.png", new Byte[]{ 12, 34, 45, 67, 78, 91 });
    }

    private PictureProposal createPictureProposal() {
        return new PictureProposal("profile.png", new Byte[]{ 12, 34, 45, 67, 78, 91 });
    }
}
