package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.model.SavedPost;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.SavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.UserProposal;
import de.tinf22b6.dhbwhub.repository.SavedPostRepository;
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
public class SavedPostServiceImplTests {
    @Mock
    private SavedPostRepository savedPostRepository;

    @InjectMocks
    private SavedPostServiceImpl savedPostService;

    @Test
    void GetAll_HasSize_Two() {
        SavedPost savedPost1 = new SavedPost(null,null);
        SavedPost savedPost2 = new SavedPost(null, null);
        when(savedPostRepository.findAll()).thenReturn(List.of(savedPost1, savedPost2));

        assertThat(savedPostService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(savedPostService.getAll()).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        SavedPost savedPost = new SavedPost(null,null);
        lenient().when(savedPostRepository.find(1L)).thenReturn(savedPost);

        assertThat(savedPostService.get(1L)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> savedPostService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Create_IsNotNull() {
        User user = new User(19, "Ich studiere Informatik", null, null);
        Post post = new Post("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, null, null, null, null);
        SavedPost savedPost = new SavedPost(user,post);
        when(savedPostRepository.save(any(SavedPost.class))).thenReturn(savedPost);

        UserProposal userProposal = new UserProposal(19, "Ich studiere Informatik", null, null);
        PostProposal postProposal = new PostProposal("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, null, null, null, null);
        SavedPostProposal savedPostProposal = new SavedPostProposal( userProposal, postProposal);
        assertThat(savedPostService.create(savedPostProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        SavedPost savedPost = new SavedPost( null, null);
        when(savedPostRepository.find(1L)).thenReturn(savedPost);

        assertDoesNotThrow(() -> savedPostService.delete(1L));
    }
}
