package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.proposal.simplified_models.CreateSavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.DeleteSavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.HomepageSavedPostProposal;
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
    void GetSavedPosts_StatusIsOk() throws Exception {
        HomepageSavedPostProposal savedPost = createHomepageSavedPostProposal();

        when(savedPostService.getSavedPostsByUserId(1L)).thenReturn(List.of(savedPost, savedPost));

        ResultActions response = mockMvc.perform(get("/saved-post/homepage-saved-posts/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void CreateSavedPost_StatusIsOk() throws Exception {
        CreateSavedPostProposal createSavedPostProposal = createCreateSavedPostProposal();
        given(savedPostService.createSavedPost(any(CreateSavedPostProposal.class))).willAnswer(i -> createHomepageSavedPostProposal());

        ResultActions response = mockMvc.perform(post("/saved-post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createSavedPostProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        DeleteSavedPostProposal deleteSavedPostProposal = createDeleteSavedPostProposal();
         doNothing().when(savedPostService).delete(any(DeleteSavedPostProposal.class));

        ResultActions response = mockMvc.perform(delete("/saved-post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(deleteSavedPostProposal)));

        response.andExpect(status().isNoContent());
    }
}
