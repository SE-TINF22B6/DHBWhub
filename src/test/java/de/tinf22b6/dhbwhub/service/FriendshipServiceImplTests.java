package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.Friendship;
import de.tinf22b6.dhbwhub.proposal.AccountProposal;
import de.tinf22b6.dhbwhub.proposal.FriendshipProposal;
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
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class FriendshipServiceImplTests {
    @Mock
    private FriendshipRepository friendshipRepository;

    @InjectMocks
    private FriendshipServiceImpl friendshipService;

    @Test
    void GetAll_HasSize_Two() {
        Account account1 = new Account("maxmustermann1234", "max@mustermann.de", "1234", null,true);
        Account account2 = new Account("miajulia1989", "miajulia89@gmx.de", "h9zdnh9hauidaw", null,true);
        Friendship friendship1 = new Friendship(account1,account2, false);
        Friendship friendship2 = new Friendship(account2,account1, false);

        when(friendshipRepository.findAll()).thenReturn(List.of(friendship1, friendship2));

        assertThat(friendshipService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(friendshipService.getAll()).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        Account account1 = new Account("maxmustermann1234", "max@mustermann.de", "1234", null,true);
        Account account2 = new Account("miajulia1989", "miajulia89@gmx.de", "h9zdnh9hauidaw", null,true);
        Friendship friendship = new Friendship(account1,account2, false);
        lenient().when(friendshipRepository.find(1L)).thenReturn(friendship);

        assertThat(friendshipService.get(1L)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> friendshipService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Create_IsNotNull() {
        Account account1 = new Account("maxmustermann1234", "max@mustermann.de", "1234", null,true);
        Account account2 = new Account("miajulia1989", "miajulia89@gmx.de", "h9zdnh9hauidaw", null,true);
        Friendship friendship = new Friendship(account1,account2, false);

        when(friendshipRepository.save(any(Friendship.class))).thenReturn(friendship);

        AccountProposal accountProposal1 = new AccountProposal("maxmustermann1234", "max@mustermann.de", "1234", null,true);
        AccountProposal accountProposal2 = new AccountProposal("miajulia1989", "miajulia89@gmx.de", "h9zdnh9hauidaw", null,true);
        FriendshipProposal friendshipProposal = new FriendshipProposal(accountProposal1,accountProposal2,false);

        assertThat(friendshipService.create(friendshipProposal)).isNotNull();
    }

    @Test
    void Update_IsNotNull() {
        Account account1 = new Account("maxmustermann1234", "max@mustermann.de", "1234", null,true);
        Account account2 = new Account("miajulia1989", "miajulia89@gmx.de", "h9zdnh9hauidaw", null,true);
        Friendship friendship = new Friendship(account1,account2, false);

        when(friendshipRepository.find(1L)).thenReturn(friendship);
        when(friendshipRepository.save(any(Friendship.class))).thenReturn(friendship);

        AccountProposal accountProposal1 = new AccountProposal("maxmustermann1234", "max@mustermann.de", "1234", null,true);
        AccountProposal accountProposal2 = new AccountProposal("miajulia1989", "miajulia89@gmx.de", "h9zdnh9hauidaw", null,true);
        FriendshipProposal friendshipProposal = new FriendshipProposal(accountProposal1,accountProposal2,false);

        assertThat(friendshipService.update(1L, friendshipProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        Account account1 = new Account("maxmustermann1234", "max@mustermann.de", "1234", null,true);
        Account account2 = new Account("miajulia1989", "miajulia89@gmx.de", "h9zdnh9hauidaw", null,true);
        Friendship friendship = new Friendship(account1,account2, false);

        when(friendshipRepository.find(1L)).thenReturn(friendship);

        assertDoesNotThrow(() -> friendshipService.delete(1L));
    }
}
