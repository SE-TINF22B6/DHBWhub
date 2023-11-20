package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.repository.PostRepository;
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
public class PostServiceImplTests {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    void GetAll_HasSize_Two() {
        Post post1 = new Post("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, null, null, null, null);
        Post post2 = new Post("Titel 2", "Beschreibung 2", new Date(1478979183L), 555, null, null, null, null);
        when(postRepository.findAll()).thenReturn(List.of(post1, post2));

        assertThat(postService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(postService.getAll()).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        Post post = new Post("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, null, null, null, null);
        lenient().when(postRepository.find(1L)).thenReturn(post);

        assertThat(postService.get(1L)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> postService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Create_IsNotNull() {
        Post post = new Post("Titel 2", "Beschreibung 2", new Date(1478979183L), 444, null, null, null, null);
        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostProposal postProposal = new PostProposal("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, null, null, null, null);
        assertThat(postService.create(postProposal)).isNotNull();
    }

    @Test
    void Update_IsNotNull() {
        Post post = new Post("Titel 2", "Beschreibung 2", new Date(1478979183L), 444, null, null, null, null);
        when(postRepository.find(1L)).thenReturn(post);
        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostProposal postProposal = new PostProposal("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, null, null, null, null);
        assertThat(postService.update(1L, postProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        Post post = new Post("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, null, null, null, null);
        when(postRepository.find(1L)).thenReturn(post);

        assertDoesNotThrow(() -> postService.delete(1L));
    }
}
