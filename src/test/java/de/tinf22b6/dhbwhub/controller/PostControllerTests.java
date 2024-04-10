package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.mapper.PostMapper;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.service.PostServiceImpl;
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

import java.sql.Date;
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

@WebMvcTest(controllers = PostController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PostControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostServiceImpl postService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GetAll_StatusIsOk() throws Exception {
        Post post = new Post("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, null, null, null, null);
        when(postService.getAll()).thenReturn(List.of(post, post));

        ResultActions response = mockMvc.perform(get("/post")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void Get_StatusIsOk() throws Exception {
        Post post = new Post("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, null, null, null, null);
        when(postService.get(any(Long.class))).thenReturn(post);

        ResultActions response = mockMvc.perform(get("/post/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(post.getTitle())))
                .andExpect(jsonPath("$.description", is(post.getDescription())))
                .andExpect(jsonPath("$.likes", is(post.getLikes())));
    }

    @Test
    void Create_StatusIsOk() throws Exception {
        PostProposal postProposal = new PostProposal("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, null, null, null, null);
        given(postService.create(any(PostProposal.class))).willAnswer(i -> PostMapper.mapToModel(i.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(postProposal.getTitle())))
                .andExpect(jsonPath("$.description", is(postProposal.getDescription())))
                .andExpect(jsonPath("$.likes", is(postProposal.getLikes())));
    }

    @Test
    void Update_StatusIsOk() throws Exception {
        PostProposal postProposal = new PostProposal("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, null, null, null, null);
        when(postService.update(any(Long.class), any(PostProposal.class))).thenReturn(PostMapper.mapToModel(postProposal));

        ResultActions response = mockMvc.perform(put("/post/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(postProposal.getTitle())))
                .andExpect(jsonPath("$.description", is(postProposal.getDescription())))
                .andExpect(jsonPath("$.likes", is(postProposal.getLikes())));
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        doNothing().when(postService).delete(any(Long.class));

        ResultActions response = mockMvc.perform(delete("/post/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }

}