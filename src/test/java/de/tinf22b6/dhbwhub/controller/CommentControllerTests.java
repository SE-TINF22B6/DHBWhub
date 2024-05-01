package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.mapper.CommentMapper;
import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;
import de.tinf22b6.dhbwhub.service.CommentServiceImpl;
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

@WebMvcTest(controllers = CommentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class CommentControllerTests extends AbstractApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentServiceImpl commentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GetAll_StatusIsOk() throws Exception {
        Comment comment = createDefaultComment();
        when(commentService.getAll()).thenReturn(List.of(comment, comment));

        ResultActions response = mockMvc.perform(get("/comment")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void Get_StatusIsOk() throws Exception {
        Comment comment = createDefaultComment();
        when(commentService.get(any(Long.class))).thenReturn(comment);

        ResultActions response = mockMvc.perform(get("/comment/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(comment.getDescription())))
                .andExpect(jsonPath("$.likes", is(comment.getLikes())));
    }

    @Test
    void Create_StatusIsOk() throws Exception {
        CommentProposal commentProposal = createDefaultCommentProposal();
        given(commentService.create(any(CommentProposal.class))).willAnswer(i -> CommentMapper.mapToModel(i.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(commentProposal.getDescription())))
                .andExpect(jsonPath("$.likes", is(commentProposal.getLikes())));
    }

    @Test
    void Update_StatusIsOk() throws Exception {
        CommentProposal commentProposal = createDefaultCommentProposal();
        when(commentService.update(any(Long.class), any(CommentProposal.class))).thenReturn(CommentMapper.mapToModel(commentProposal));

        ResultActions response = mockMvc.perform(put("/comment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(commentProposal.getDescription())))
                .andExpect(jsonPath("$.likes", is(commentProposal.getLikes())));
    }

    @Test
    void CreateComment_StatusIsOk() throws Exception {
        CreateCommentProposal commentProposal = createDefaultCreateCommentProposal();
        CommentThreadViewProposal commentThreadViewProposal = createDefaultCommentThreadViewProposal();

        given(commentService.create(any(CreateCommentProposal.class))).willAnswer(i -> commentThreadViewProposal);

        ResultActions response = mockMvc.perform(post("/comment/create-comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(commentProposal.getDescription())));
    }

    @Test
    void UpdateComment_StatusIsOk() throws Exception {
        UpdateCommentProposal commentProposal = createDefaultUpdateCommentProposal();
        CommentThreadViewProposal commentThreadViewProposal = createDefaultCommentThreadViewProposal();

        when(commentService.update(any(Long.class), any(UpdateCommentProposal.class))).thenReturn(commentThreadViewProposal);

        ResultActions response = mockMvc.perform(put("/comment/update-comment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(commentProposal.getDescription())));
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        doNothing().when(commentService).delete(any(Long.class));

        ResultActions response = mockMvc.perform(delete("/comment/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }

    @Test
    void IncreaseLikes_StatusIsOk() throws Exception {
        Integer likes = 1;
        when(commentService.increaseLikes(any(Long.class))).thenReturn(1);

        ResultActions response = mockMvc.perform(put("/comment/increase-likes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(likes)));

        response.andExpect(status().isOk());
    }

    @Test
    void DecreaseLikes_StatusIsOk() throws Exception {
        Integer likes = 0;
        when(commentService.decreaseLikes(any(Long.class))).thenReturn(0);

        ResultActions response = mockMvc.perform(put("/comment/decrease-likes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(likes)));

        response.andExpect(status().isOk());
    }
}
