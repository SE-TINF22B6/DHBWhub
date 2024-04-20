package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.mapper.SavedPostMapper;
import de.tinf22b6.dhbwhub.model.SavedPost;
import de.tinf22b6.dhbwhub.proposal.SavedPostProposal;
import de.tinf22b6.dhbwhub.service.SavedPostServiceImpl;
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

@WebMvcTest(controllers = SavedPostController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class SavedPostControllerTests extends AbstractApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SavedPostServiceImpl savedPostService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GetAll_StatusIsOk() throws Exception {
        SavedPost savedPost = createDefaultSavedPost();

        when(savedPostService.getAll()).thenReturn(List.of(savedPost, savedPost));

        ResultActions response = mockMvc.perform(get("/savedPost")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void Get_StatusIsOk() throws Exception {
        SavedPost savedPost = createDefaultSavedPost();

        when(savedPostService.get(any(Long.class))).thenReturn(savedPost);

        ResultActions response = mockMvc.perform(get("/savedPost/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

    @Test
    void Create_StatusIsOk() throws Exception {
        SavedPostProposal savedPostProposal = createDefaultSavedPostProposal();

        given(savedPostService.create(any(SavedPostProposal.class))).willAnswer(i -> SavedPostMapper.mapToModel(i.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/savedPost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedPostProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        doNothing().when(savedPostService).delete(any(Long.class));

        ResultActions response = mockMvc.perform(delete("/savedPost/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }
}
