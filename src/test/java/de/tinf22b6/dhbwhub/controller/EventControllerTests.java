package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;
import de.tinf22b6.dhbwhub.service.EventServiceImpl;
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
@WebMvcTest(controllers = EventController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class EventControllerTests extends AbstractApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventServiceImpl eventService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GetEventPreviewsHomepage_StatusIsOk() throws Exception {
        HomepageEventPreviewProposal homepageEvent = createDefaultEventPostPreviewProposal();
        when(eventService.getHomepageEvents()).thenReturn(List.of(homepageEvent, homepageEvent));

        ResultActions response = mockMvc.perform(get("/event/homepage-preview-events")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void GetEventThreadView_StatusIsOk() throws Exception {
        EventThreadViewProposal eventThreadView = createDefaultEventThreadViewProposal();
        when(eventService.getEventThreadView(1L)).thenReturn(eventThreadView);

        ResultActions response = mockMvc.perform(get("/event/event-thread/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(eventThreadView.getTitle())))
                .andExpect(jsonPath("$.description", is(eventThreadView.getDescription())))
                .andExpect(jsonPath("$.likeAmount", is(eventThreadView.getLikeAmount())));
    }

    @Test
    void GetEventComments_StatusIsOk() throws Exception {
        EventCommentThreadViewProposal eventCommentThreadView = createDefaultEventCommentThreadViewProposal();
        when(eventService.getEventComments(1L)).thenReturn(List.of(eventCommentThreadView,eventCommentThreadView));

        ResultActions response = mockMvc.perform(get("/event/event-comments/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void GetCommentThreadView_StatusIsOk() throws Exception {
        EventCommentThreadViewProposal commentThreadView = createDefaultEventCommentThreadViewProposal();
        when(eventService.getEventCommentThreadView(1L)).thenReturn(commentThreadView);

        ResultActions response = mockMvc.perform(get("/event/event-comment/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is(commentThreadView.getText())))
                .andExpect(jsonPath("$.likeAmount", is(commentThreadView.getLikeAmount())));
    }
    @Test
    void CreateEvent_StatusIsOk() throws Exception {
        CreateEventPostProposal eventPostProposal = createDefaultCreateEventPostProposal();
        EventThreadViewProposal eventThreadViewProposal = createDefaultEventThreadViewProposal();

        given(eventService.create(any(CreateEventPostProposal.class))).willAnswer(i -> eventThreadViewProposal);

        ResultActions response = mockMvc.perform(post("/event/create-event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventPostProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(eventPostProposal.getTitle())))
                .andExpect(jsonPath("$.description", is(eventPostProposal.getDescription())));
    }

    @Test
    void UpdateEvent_StatusIsOk() throws Exception {
        UpdateEventPostProposal eventPostProposal = createDefaultUpdateEventPostProposal();
        EventThreadViewProposal eventThreadViewProposal = createDefaultEventThreadViewProposal();

        when(eventService.update(any(Long.class), any(UpdateEventPostProposal.class))).thenReturn(eventThreadViewProposal);

        ResultActions response = mockMvc.perform(put("/event/update-event/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventPostProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(eventPostProposal.getTitle())))
                .andExpect(jsonPath("$.description", is(eventPostProposal.getDescription())));
    }

    @Test
    void IncreaseLikesPost_StatusIsOk() throws Exception {
        Integer likes = 1;
        LikeEventPostProposal likeEventPostProposal = createDefaultLikeEventPostProposal();

        when(eventService.adjustPostLikes(any(LikeEventPostProposal.class),any(Integer.class))).thenReturn(2);

        ResultActions response = mockMvc.perform(put("/event/post-increase-likes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(likeEventPostProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void DecreaseLikesPost_StatusIsOk() throws Exception {
        Integer likes = 0;
        LikeEventPostProposal likeEventPostProposal = createDefaultLikeEventPostProposal();
        when(eventService.adjustPostLikes(any(LikeEventPostProposal.class),any(Integer.class))).thenReturn(0);

        ResultActions response = mockMvc.perform(put("/event/post-decrease-likes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(likeEventPostProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void IncreaseLikesComment_StatusIsOk() throws Exception {
        Integer likes = 1;
        LikeEventCommentProposal likeEventCommentProposal = createDefaultLikeEventCommentProposal();

        when(eventService.adjustCommentLikes(any(LikeEventCommentProposal.class),any(Integer.class))).thenReturn(2);

        ResultActions response = mockMvc.perform(put("/event/comment-increase-likes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(likeEventCommentProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void DecreaseLikesComment_StatusIsOk() throws Exception {
        Integer likes = 0;
        LikeEventCommentProposal likeEventCommentProposal = createDefaultLikeEventCommentProposal();

        when(eventService.adjustCommentLikes(any(LikeEventCommentProposal.class),any(Integer.class))).thenReturn(0);

        ResultActions response = mockMvc.perform(put("/event/comment-decrease-likes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(likeEventCommentProposal)));

        response.andExpect(status().isOk());
    }

    @Test
    void DeleteEvent_StatusIsOk() throws Exception {
        doNothing().when(eventService).deletePost(any(Long.class));
        ResultActions response = mockMvc.perform(delete("/event/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(status().isNoContent());
    }

    @Test
    void DeleteComment_StatusIsOk() throws Exception {
        doNothing().when(eventService).deleteComment(any(Long.class));
        ResultActions response = mockMvc.perform(delete("/event/comment/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(status().isNoContent());
    }
}
