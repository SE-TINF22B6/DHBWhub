package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtablePostComment;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;
import de.tinf22b6.dhbwhub.repository.CommentRepository;
import de.tinf22b6.dhbwhub.repository.LogtableRepository;
import de.tinf22b6.dhbwhub.repository.NotificationRepository;
import de.tinf22b6.dhbwhub.repository.UserRepository;
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

    @Mock
    private UserRepository userRepository;

    @Mock
    private LogtableRepository logtableRepository;

    @Mock
    private NotificationRepository notificationRepository;

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
        comment.getUser().setId(0L);
        Comment updatedComment = createUpdatedDefaultComment();
        User user = createDefaultUser();
        user.setId(1L);

        when(commentRepository.find(1L)).thenReturn(comment);
        when(commentRepository.save(any(Comment.class))).thenReturn(updatedComment);
        when(userRepository.find(1L)).thenReturn(user);
        when(logtableRepository.checkIfCommentExists(comment.getId(),user.getId())).thenReturn(false);

        assertThat(commentService.increaseLikes(createDefaultLikeCommentProposal())).isEqualTo(5);

    }

    @Test
    void DecreaseLikes_ValueDecreased() {
        Comment comment = createDefaultComment();
        Comment updatedComment = createUpdatedDefaultComment2();
        LikeLogtablePostComment likeLogtablePostComment = createLikeLogtablePostComment();
        likeLogtablePostComment.setId(1L);

        when(commentRepository.find(1L)).thenReturn(comment);
        when(commentRepository.save(any(Comment.class))).thenReturn(updatedComment);
        when(logtableRepository.findComment(1L, 1L)).thenReturn(createLikeLogtablePostComment());

        assertThat(commentService.decreaseLikes(createDefaultLikeCommentProposal())).isEqualTo(3);
    }
}
