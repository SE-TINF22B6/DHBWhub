package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;
import de.tinf22b6.dhbwhub.repository.CommentRepository;
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
class CommentServiceImplTests extends AbstractApplicationTest {
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void GetAll_HasSize_Two() {
        Comment comment1 = createDefaultComment();
        Comment comment2 = createDefaultComment2();
        when(commentRepository.findAll()).thenReturn(List.of(comment1, comment2));

        assertThat(commentService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(commentService.getAll()).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        Comment comment = createDefaultComment();
        when(commentRepository.find(1L)).thenReturn(comment);

        assertThat(commentService.get(1L)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> commentService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Create_IsNotNull() {
        Comment comment = createDefaultComment();
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        CommentProposal commentProposal = createDefaultCommentProposal();
        assertThat(commentService.create(commentProposal)).isNotNull();
    }

    @Test
    void Update_IsNotNull() {
        Comment comment = createDefaultComment();
        when(commentRepository.find(1L)).thenReturn(comment);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        CommentProposal commentProposal = createDefaultCommentProposal();
        assertThat(commentService.update(1L, commentProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        Comment comment = createDefaultComment();
        when(commentRepository.find(1L)).thenReturn(comment);

        assertDoesNotThrow(() -> commentService.delete(1L));
    }

    @Test
    void IncreaseLikes_ValueIncreased() {
        Comment comment = createDefaultComment();
        Comment updatedComment = createUpdatedDefaultComment();

        when(commentRepository.find(1L)).thenReturn(comment);
        when(commentRepository.save(any(Comment.class))).thenReturn(updatedComment);

        assertThat(commentService.increaseLikes(1L)).isEqualTo(5);
    }

    @Test
    void IncreaseLikes_ValueDecreased() {
        Comment comment = createDefaultComment();
        Comment updatedComment = createUpdatedDefaultComment2();

        when(commentRepository.find(1L)).thenReturn(comment);
        when(commentRepository.save(any(Comment.class))).thenReturn(updatedComment);

        assertThat(commentService.increaseLikes(1L)).isEqualTo(3);
    }
}
