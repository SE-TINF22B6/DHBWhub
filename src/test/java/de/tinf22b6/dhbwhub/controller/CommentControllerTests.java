package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.mapper.CommentMapper;
import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;
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

@WebMvcTest(controllers = CommentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class CommentControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentServiceImpl commentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GetAll_StatusIsOk() throws Exception {
        Comment comment = new Comment("Das ist ganz normaler Kommentar", new Date(1701242553L), 4, null, null, null);
        when(commentService.getAll()).thenReturn(List.of(comment, comment));

        ResultActions response = mockMvc.perform(get("/comment")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void Get_StatusIsOk() throws Exception {
        Comment comment = new Comment("Das ist ganz normaler Kommentar", new Date(1701242553L), 4, null, null, null);
        when(commentService.get(any(Long.class))).thenReturn(comment);

        ResultActions response = mockMvc.perform(get("/comment/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(comment.getDescription())))
                .andExpect(jsonPath("$.likes", is(comment.getLikes())));
    }

    @Test
    void Create_StatusIsOk() throws Exception {
        CommentProposal commentProposal = new CommentProposal("Das ist ganz normaler Kommentar", new Date(1701242817893L), 4, null, null, null);
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
        CommentProposal commentProposal = new CommentProposal("Das ist ganz normaler Kommentar", new Date(1701242553L), 4, null, null, null);
        when(commentService.update(any(Long.class), any(CommentProposal.class))).thenReturn(CommentMapper.mapToModel(commentProposal));

        ResultActions response = mockMvc.perform(put("/comment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(commentProposal.getDescription())))
                .andExpect(jsonPath("$.likes", is(commentProposal.getLikes())));
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        doNothing().when(commentService).delete(any(Long.class));

        ResultActions response = mockMvc.perform(delete("/comment/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }
}
