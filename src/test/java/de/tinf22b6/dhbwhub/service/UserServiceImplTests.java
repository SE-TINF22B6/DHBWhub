package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.UserProposal;
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
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserServiceImplTests {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void GetAll_HasSize_Two() {
        User user1 = new User(19, "Ich studiere Informatik", null, null);
        User user2 = new User(21, "Ich studiere Jura", null, null);
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        assertThat(userService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(userService.getAll()).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        User user = new User(19, "Ich studiere Informatik", null, null);
        lenient().when(userRepository.find(1L)).thenReturn(user);

        assertThat(userService.get(1L)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> userService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Create_IsNotNull() {
        User user = new User(19, "Ich studiere Informatik", null, null);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserProposal userProposal = new UserProposal(19, "Ich studiere Informatik", null, null);
        assertThat(userService.create(userProposal)).isNotNull();
    }

    @Test
    void Update_IsNotNull() {
        User user = new User(19, "Ich studiere Informatik", null, null);
        when(userRepository.find(1L)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserProposal userProposal = new UserProposal(19, "Ich studiere Informatik", null, null);
        assertThat(userService.update(1L, userProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        User user = new User(19, "Ich studiere Informatik", null, null);
        when(userRepository.find(1L)).thenReturn(user);

        assertDoesNotThrow(() -> userService.delete(1L));
    }
}
