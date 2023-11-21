package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;
import de.tinf22b6.dhbwhub.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class CommentServiceImplTests {
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void GetAll_HasSize_Two() {
        Comment comment1 = new Comment("Das ist ganz normaler Kommentar", new Date(1478979207L), 4, null, null, null);
        Comment comment2 = new Comment("Du mieser Hund!", new Date(1478979183L), 5, null, null, null);
        when(commentRepository.findAll()).thenReturn(List.of(comment1, comment2));

        assertThat(commentService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(commentService.getAll()).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        Comment comment = new Comment("Das ist ganz normaler Kommentar", new Date(1478979207L), 4, null, null, null);
        lenient().when(commentRepository.find(1L)).thenReturn(comment);

        assertThat(commentService.get(1L)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> commentService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Create_IsNotNull() {
        Comment comment = new Comment("Das ist ganz normaler Kommentar", new Date(1478979207L), 4, null, null, null);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        CommentProposal commentProposal = new CommentProposal("Das ist ganz normaler Kommentar", new Date(1478979207L), 4, null, null, null);
        assertThat(commentService.create(commentProposal)).isNotNull();
    }

    @Test
    void Update_IsNotNull() {
        Comment comment = new Comment("Das ist ganz normaler Kommentar", new Date(1478979207L), 4, null, null, null);
        when(commentRepository.find(1L)).thenReturn(comment);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        CommentProposal commentProposal = new CommentProposal("Das ist ganz normaler Kommentar", new Date(1478979207L), 4, null, null, null);
        assertThat(commentService.update(1L, commentProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        Comment comment = new Comment("Das ist ganz normaler Kommentar", new Date(1478979207L), 4, null, null, null);
        when(commentRepository.find(1L)).thenReturn(comment);

        assertDoesNotThrow(() -> commentService.delete(1L));
    }
}
