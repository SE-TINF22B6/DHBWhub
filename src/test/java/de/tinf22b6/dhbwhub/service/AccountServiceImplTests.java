package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.proposal.AccountProposal;
import de.tinf22b6.dhbwhub.repository.AccountRepository;
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
public class AccountServiceImplTests {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void GetAll_HasSize_Two() {
        Picture picture1 = createPicture();
        Account account1 = new Account("maxmustermann1234", "max@mustermann.de", "1234", picture1, true);
        Picture picture2 = createPicture();
        Account account2 = new Account("miajulia1989", "miajulia89@gmx.de", "h9zdnh9hauidaw", picture2, true);
        when(accountRepository.findAll()).thenReturn(List.of(account1, account2));

        assertThat(accountService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(accountService.getAll()).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        Account account = new Account("maxmustermann1234", "max@mustermann.de", "1234", null,true);
        lenient().when(accountRepository.find(1L)).thenReturn(account);

        assertThat(accountService.get(1L)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> accountService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Create_IsNotNull() {
        Account account = new Account("maxmustermann1234", "max@mustermann.de", "1234", null,true);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountProposal accountProposal = new AccountProposal("maxmustermann1234", "max@mustermann.de", "1234", null,true);
        assertThat(accountService.create(accountProposal)).isNotNull();
    }

    @Test
    void Update_IsNotNull() {
        Account account = new Account("maxmustermann1234", "max@mustermann.de", "1234", null, true);
        when(accountRepository.find(1L)).thenReturn(account);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountProposal accountProposal = new AccountProposal("maxmustermann1234", "max@mustermann.de", "1234", null,true);
        assertThat(accountService.update(1L, accountProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        Account account = new Account("maxmustermann1234", "max@mustermann.de", "1234", null,true);
        when(accountRepository.find(1L)).thenReturn(account);

        assertDoesNotThrow(() -> accountService.delete(1L));
    }

    private Picture createPicture() {
        return new Picture("profile.png", new Byte[]{ 12, 34, 45, 67, 78, 91 });
    }
}
