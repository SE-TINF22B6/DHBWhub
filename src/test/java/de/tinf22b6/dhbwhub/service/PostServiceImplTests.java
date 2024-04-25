package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.CommentThreadViewProposal;
import de.tinf22b6.dhbwhub.repository.PostRepository;
import org.checkerframework.checker.units.qual.C;
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
class PostServiceImplTests extends AbstractApplicationTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    void GetAll_HasSize_Two() {
        Post post1 = createDefaultPost();
        Post post2 = createDefaultPost2();
        when(postRepository.findAll()).thenReturn(List.of(post1, post2));

        assertThat(postService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(postService.getAll()).isEmpty();
    }

    @Test
    void GetHomepagePosts_HasSize_Two() {
        Post post1 = createDefaultPost();
        Post post2 = createDefaultPost2();
        when(postRepository.findHomepagePosts()).thenReturn(List.of(post1, post2));

        assertThat(postService.getHomepagePosts()).hasSize(2);
    }

    @Test
    void GetHomepagePosts_IsEmpty() {
        assertThat(postService.getHomepagePosts()).isEmpty();
    }

    @Test
    void GetFacPosts_HasSize_Two() {
        Post post1 = createDefaultPost();
        Post post2 = createDefaultPost2();

        when(postRepository.findPostsFromFacWirtschaft()).thenReturn(List.of(post1, post2));
        when(postRepository.findPostsFromFacTechnik()).thenReturn(List.of(post1));
        when(postRepository.findPostsFromFacGesundheit()).thenReturn(List.of(post1));

        assertThat(postService.getFacPosts(0L)).hasSize(1);
        assertThat(postService.getFacPosts(1L)).hasSize(1);
        assertThat(postService.getFacPosts(2L)).hasSize(2);

    }

    @Test
    void GetFacPosts_IsEmpty() {
        assertThat(postService.getFacPosts(1L)).isEmpty();
    }

    @Test
    void GetPostComments_HasSize_Two() {
        CommentThreadViewProposal comment1 = createDefaultCommentThreadViewProposal();
        CommentThreadViewProposal comment2 = createDefaultCommentThreadViewProposal();

        when(postRepository.getPostComments(1L)).thenReturn(List.of(comment1, comment2));

        assertThat(postService.getPostComments(1L)).hasSize(2);
    }

    @Test
    void GetPostComments_IsEmpty() {
        assertThat(postService.getPostComments(1L)).isEmpty();
    }

    @Test
    void GetPostTags_HasSize_Two() {
        String tag1 = "Tag 1";
        String tag2 = "Tag 2";

        when(postRepository.getPostTags(1L)).thenReturn(List.of(tag1, tag2));

        assertThat(postService.getPostTags(1L)).hasSize(2);
    }

    @Test
    void GetPostTags_IsEmpty() {
        assertThat(postService.getPostTags(1L)).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        Post post = createDefaultPost();
        when(postRepository.find(1L)).thenReturn(post);

        assertThat(postService.get(1L)).isNotNull();
    }

    @Test
    void GetPostThreadView_IsNotNull() {
        Post post = createDefaultPost();
        when(postRepository.find(1L)).thenReturn(post);

        assertThat(postService.getPostThreadView(1L)).isNotNull();
    }

    @Test
    void GetPostThreadView_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> postService.getPostThreadView(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> postService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Create_IsNotNull() {
        Post post = createDefaultPost();
        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostProposal postProposal = createDefaultPostProposal();
        assertThat(postService.create(postProposal)).isNotNull();
    }

    @Test
    void Update_IsNotNull() {
        Post post = createDefaultPost();
        when(postRepository.find(1L)).thenReturn(post);
        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostProposal postProposal = createDefaultPostProposal();
        assertThat(postService.update(1L, postProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        Post post = createDefaultPost();
        when(postRepository.find(1L)).thenReturn(post);

        assertDoesNotThrow(() -> postService.delete(1L));
    }
}
