package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.simplified_models.FollowUserProposal;
import de.tinf22b6.dhbwhub.repository.AccountRepository;
import de.tinf22b6.dhbwhub.repository.FriendshipRepository;
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
class FriendshipServiceImplTests extends AbstractApplicationTest {
    @Mock
    private FriendshipRepository friendshipRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private NotificationRepository notificationRepository;

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

        User requestingUser = createDefaultUser();
        requestingUser.setId(0L);
        User receiveingUser = createDefaultUser2();
        receiveingUser.setId(1L);

        when(userRepository.find(0L)).thenReturn(requestingUser);
        when(userRepository.find(1L)).thenReturn(receiveingUser);
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
