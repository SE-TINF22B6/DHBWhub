package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.proposal.FriendshipProposal;
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

    @InjectMocks
    private FriendshipServiceImpl friendshipService;

    @Test
    void GetAll_HasSize_Two() {
        Friendship friendship1 = createDefaultFriendship();
        Friendship friendship2 = createDefaultFriendship2();

        when(friendshipRepository.findAll()).thenReturn(List.of(friendship1, friendship2));

        assertThat(friendshipService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(friendshipService.getAll()).isEmpty();
    }

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
    void GetFriendrequests_HasSize_Two() {
        Friendship friendship1 = createDefaultFriendship();
        Friendship friendship2 = createDefaultFriendship2();

        when(friendshipRepository.getSentFriendrequests(1L)).thenReturn(List.of(friendship1));
        when(friendshipRepository.getReceivedFriendrequests(1L)).thenReturn(List.of(friendship2));

        assertThat(friendshipService.getFriendrequests(1L)).hasSize(2);
    }

    @Test
    void GetFriendrequests_IsEmpty() {
        assertThat(friendshipService.getFriendrequests(1L)).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        Friendship friendship = createDefaultFriendship();
        when(friendshipRepository.find(1L)).thenReturn(friendship);

        assertThat(friendshipService.get(1L)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> friendshipService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Create_IsNotNull() {
        Friendship friendship = createDefaultFriendship();

        when(friendshipRepository.save(any(Friendship.class))).thenReturn(friendship);

        FriendshipProposal friendshipProposal = createDefaultFriendshipProposal();

        assertThat(friendshipService.create(friendshipProposal)).isNotNull();
    }

    @Test
    void Update_IsNotNull() {
        Friendship friendship = createDefaultFriendship();

        when(friendshipRepository.find(1L)).thenReturn(friendship);
        when(friendshipRepository.save(any(Friendship.class))).thenReturn(friendship);

        FriendshipProposal friendshipProposal = createDefaultFriendshipProposal();

        assertThat(friendshipService.update(1L, friendshipProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        Friendship friendship = createDefaultFriendship();

        when(friendshipRepository.find(1L)).thenReturn(friendship);

        assertDoesNotThrow(() -> friendshipService.delete(1L));
    }
}
