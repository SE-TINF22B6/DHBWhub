package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.model.SavedPost;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.SavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.CreateSavedPostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.DeleteSavedPostProposal;
import de.tinf22b6.dhbwhub.repository.PostRepository;
import de.tinf22b6.dhbwhub.repository.SavedPostRepository;
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
class SavedPostServiceImplTests extends AbstractApplicationTest {
    @Mock
    private SavedPostRepository savedPostRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SavedPostServiceImpl savedPostService;

    @Test
    void GetAll_HasSize_Two() {
        SavedPost savedPost1 = createDefaultSavedPost();
        SavedPost savedPost2 = createDefaultSavedPost2();
        when(savedPostRepository.findAll()).thenReturn(List.of(savedPost1, savedPost2));

        assertThat(savedPostService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(savedPostService.getAll()).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        SavedPost savedPost = createDefaultSavedPost();
        when(savedPostRepository.find(1L)).thenReturn(savedPost);

        assertThat(savedPostService.get(1L)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> savedPostService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }


    @Test
    void GetSavedPostsByUserId_HasSize_Two(){
        SavedPost savedPost1 = createDefaultSavedPost();
        SavedPost savedPost2 = createDefaultSavedPost2();
        when(savedPostRepository.findByUserId(1L)).thenReturn(List.of(savedPost1, savedPost2));

        assertThat(savedPostService.getSavedPostsByUserId(1L)).hasSize(2);
    }

    @Test
    void GetSavedPostsByUserId_IsEmpty(){
        assertThat(savedPostService.getSavedPostsByUserId(1L)).isEmpty();
    }

    @Test
    void Create_IsNotNull() {
        SavedPost savedPost = createDefaultSavedPost();
        when(savedPostRepository.save(any(SavedPost.class))).thenReturn(savedPost);

        SavedPostProposal savedPostProposal = createDefaultSavedPostProposal();
        assertThat(savedPostService.create(savedPostProposal)).isNotNull();
    }

    @Test
    void CreateSavedPost_IsNotNull(){
        SavedPost savedPost = createDefaultSavedPost();
        savedPost.setId(1L);
        savedPost.getPost().setId(1L);
        savedPost.getUser().setId(1L);

        CreateSavedPostProposal createSavedPostProposal = createCreateSavedPostProposal();
        User user = createDefaultUser();
        Post post = createDefaultPost();

        when(userRepository.find(1L)).thenReturn(user);
        when(postRepository.find(1L)).thenReturn(post);
        when(savedPostRepository.save(any(SavedPost.class))).thenReturn(savedPost);

        assertThat(savedPostService.createSavedPost(createSavedPostProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        SavedPost savedPost = createDefaultSavedPost();
        when(savedPostRepository.findByUserIdAndPostId(any(Long.class), any(Long.class))).thenReturn(List.of(savedPost));

        DeleteSavedPostProposal deleteSavedPostProposal = createDeleteSavedPostProposal();
        assertDoesNotThrow(() -> savedPostService.delete(deleteSavedPostProposal));
    }
}
