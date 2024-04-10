package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.mapper.AdministratorMapper;
import de.tinf22b6.dhbwhub.model.Administrator;
import de.tinf22b6.dhbwhub.proposal.AdministratorProposal;
import de.tinf22b6.dhbwhub.service.AdministratorServiceImpl;
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

@WebMvcTest(controllers = AdministratorController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class AdministratorControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdministratorServiceImpl administratorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GetAll_StatusIsOk() throws Exception {
        Administrator administrator = new Administrator(null);
        when(administratorService.getAll()).thenReturn(List.of(administrator, administrator));

        ResultActions response = mockMvc.perform(get("/administrator")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void Get_StatusIsOk() throws Exception {
        Administrator administrator = new Administrator(null);
        when(administratorService.get(any(Long.class))).thenReturn(administrator);

        ResultActions response = mockMvc.perform(get("/administrator/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

    @Test
    void Create_StatusIsOk() throws Exception {
        AdministratorProposal administratorProposal = new AdministratorProposal(null);
        given(administratorService.create(any(AdministratorProposal.class))).willAnswer(i -> AdministratorMapper.mapToModel(i.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/administrator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(administratorProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void Update_StatusIsOk() throws Exception {
        AdministratorProposal administratorProposal = new AdministratorProposal(null);
        when(administratorService.update(any(Long.class), any(AdministratorProposal.class))).thenReturn(AdministratorMapper.mapToModel(administratorProposal));

        ResultActions response = mockMvc.perform(put("/administrator/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(administratorProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        doNothing().when(administratorService).delete(any(Long.class));

        ResultActions response = mockMvc.perform(delete("/administrator/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }

}
