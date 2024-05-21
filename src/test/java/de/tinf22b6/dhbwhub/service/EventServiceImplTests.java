package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.model.EventComment;
import de.tinf22b6.dhbwhub.model.EventPost;
import de.tinf22b6.dhbwhub.model.EventTag;
import de.tinf22b6.dhbwhub.proposal.simplified_models.EventCommentThreadViewProposal;
import de.tinf22b6.dhbwhub.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class EventServiceImplTests extends AbstractApplicationTest {
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @Test
    void GetEventPost_IsNotNull() {
        EventPost post = createDefaultEventPost();
        when(eventRepository.findEventPost(1L)).thenReturn(post);

        assertThat(eventService.getEventPost(1L)).isNotNull();
    }

    @Test
    void GetEventComment_IsNotNull() {
        EventComment comment = createDefaultEventComment();
        when(eventRepository.findEventComment(1L)).thenReturn(comment);

        assertThat(eventService.getEventComment(1L)).isNotNull();
    }

    @Test
    void GetEventTag_IsNotNull() {
        EventTag tag = createDefaultEventTag();
        when(eventRepository.findEventTag(1L)).thenReturn(tag);

        assertThat(eventService.getEventTag(1L)).isNotNull();
    }

    @Test
    void GetHomepageEvents_IsEmpty() {
        assertThat(eventService.getHomepageEvents()).isEmpty();
    }

    @Test
    void GetHomepageEvents_HasSize_Two() {
        EventPost post1 = createDefaultEventPost();
        EventPost post2 = createDefaultEventPost();
        when(eventRepository.findAllEventPosts()).thenReturn(List.of(post1, post2));

        assertThat(eventService.getHomepageEvents()).hasSize(2);
    }

    @Test
    void GetEventThreadView_IsNotNull() {
        EventPost post = createDefaultEventPost();
        when(eventRepository.findEventPost(1L)).thenReturn(post);

        assertThat(eventService.getEventThreadView(1L)).isNotNull();
    }

    @Test
    void GetEventCommentThreadView_IsNotNull() {
        EventComment comment = createDefaultEventComment();
        when(eventRepository.findEventComment(1L)).thenReturn(comment);

        assertThat(eventService.getEventCommentThreadView(1L)).isNotNull();
    }

    @Test
    void GetEventComments_IsEmpty() {
        assertThat(eventService.getEventComments(1L)).isEmpty();
    }

    @Test
    void GetEventComments_HasSize_Two() {
        EventCommentThreadViewProposal comment1 = createDefaultEventCommentThreadViewProposal();
        EventCommentThreadViewProposal comment2 = createDefaultEventCommentThreadViewProposal();
        when(eventRepository.getEventComments(1L)).thenReturn(List.of(comment1,comment2));

        assertThat(eventService.getEventComments(1L)).hasSize(2);
    }

    @Test
    void GetEventTags_IsEmpty() {
        assertThat(eventService.getEventTags(1L)).isEmpty();
    }

    @Test
    void GetEventTags_HasSize_Two() {
        when(eventRepository.getEventTags(1L)).thenReturn(List.of("tag 1","tag 2"));

        assertThat(eventService.getEventTags(1L)).hasSize(2);
    }

    @Test
    void IncreaseLikes_ValueIncreased() {
        EventPost post = createDefaultEventPost();
        EventPost updatedPost = createUpdatedDefaultEventPost();

        EventComment comment = createDefaultEventComment();
        EventComment updatedComment = createUpdatedDefaultEventComment();

        when(eventRepository.findEventPost(1L)).thenReturn(post);
        when(eventRepository.save(any(EventPost.class))).thenReturn(updatedPost);

        when(eventRepository.findEventComment(1L)).thenReturn(comment);
        when(eventRepository.save(any(EventComment.class))).thenReturn(updatedComment);

        assertThat(eventService.adjustPostLikes(createDefaultLikeEventPostProposal(), 0)).isEqualTo(2);
        assertThat(eventService.adjustCommentLikes(createDefaultLikeEventCommentProposal(),0)).isEqualTo(2);
    }

    @Test
    void IncreaseLikes_ValueDecreased() {
        EventPost post = createDefaultEventPost();
        EventPost updatedPost = createUpdatedDefaultEventPost2();

        EventComment comment = createDefaultEventComment();
        EventComment updatedComment = createUpdatedDefaultEventComment2();

        when(eventRepository.findEventPost(1L)).thenReturn(post);
        when(eventRepository.save(any(EventPost.class))).thenReturn(updatedPost);

        when(eventRepository.findEventComment(1L)).thenReturn(comment);
        when(eventRepository.save(any(EventComment.class))).thenReturn(updatedComment);

        assertThat(eventService.adjustPostLikes(createDefaultLikeEventPostProposal(),1)).isEqualTo(0);
        assertThat(eventService.adjustCommentLikes(createDefaultLikeEventCommentProposal(),1)).isEqualTo(0);
    }

    @Test
    void DeletePost_DoesNotThrow() {
        EventPost post = createDefaultEventPost();
        when(eventRepository.findEventPost(1L)).thenReturn(post);

        assertDoesNotThrow(() -> eventService.deletePost(1L));
    }

    @Test
    void DeleteComment_DoesNotThrow() {
        EventComment comment = createDefaultEventComment();
        when(eventRepository.findEventComment(1L)).thenReturn(comment);

        assertDoesNotThrow(() -> eventService.deleteComment(1L));
    }

    @Test
    void DeleteTag_DoesNotThrow() {
        EventTag tag = createDefaultEventTag();
        when(eventRepository.findEventTag(1L)).thenReturn(tag);

        assertDoesNotThrow(() -> eventService.deleteTag(1L));
    }
}
