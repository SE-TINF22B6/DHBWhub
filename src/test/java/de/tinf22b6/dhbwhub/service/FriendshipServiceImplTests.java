package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;
import de.tinf22b6.dhbwhub.proposal.FriendshipProposal;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.FollowUserProposal;
import de.tinf22b6.dhbwhub.repository.AccountRepository;
import de.tinf22b6.dhbwhub.repository.FriendshipRepository;
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
class FriendshipServiceImplTests extends AbstractApplicationTest {
    @Mock
    private FriendshipRepository friendshipRepository;
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private FriendshipServiceImpl friendshipService;

    @Test
    void GetFriendlist_HasSize_Two() {
        Friendship friendship1 = createDefaultFriendship();
        Friendship friendship2 = createDefaultFriendship2();

        when(friendshipRepository.getFriendlist(1L)).thenReturn(List.of(friendship1, friendship2));

        assertThat(friendshipService.getFriendlist(1L)).hasSize(2);
    }

    @Test
    void GetFriendlist_IsEmpty() {
        assertThat(friendshipService.getFriendlist(1L)).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        Friendship friendship = createDefaultFriendship();
        when(friendshipRepository.find(1L)).thenReturn(friendship);

        assertThat(friendshipService.get(1L)).isNotNull();
    }

    @Test
    void FollowUser_IsNotNull() {
        Friendship friendship = createDefaultFriendship();
        FollowUserProposal followUserProposal = createDefaultFollowUserProposal();

        Account requester = createDefaultAccount();
        requester.setId(0L);
        Account receiver = createDefaultAccount2();
        receiver.setId(1L);

        when(accountRepository.find(0L)).thenReturn(requester);
        when(accountRepository.find(1L)).thenReturn(receiver);
        when(friendshipRepository.exists(any(Long.class), any(Long.class))).thenReturn(null);
        when(friendshipRepository.save(any(Friendship.class))).thenReturn(friendship);

        assertThat(friendshipService.followUser(followUserProposal)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> friendshipService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Delete_DoesNotThrow() {
        Friendship friendship = createDefaultFriendship();

        when(friendshipRepository.find(1L)).thenReturn(friendship);

        assertDoesNotThrow(() -> friendshipService.delete(1L));
    }
}
