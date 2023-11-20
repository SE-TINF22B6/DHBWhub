package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Administrator;
import de.tinf22b6.dhbwhub.proposal.AdministratorProposal;
import de.tinf22b6.dhbwhub.repository.AdministratorRepository;
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
public class AdministratorServiceImplTests {
    @Mock
    private AdministratorRepository administratorRepository;

    @InjectMocks
    private AdministratorServiceImpl administratorService;

    @Test
    void GetAll_HasSize_Two() {
        Administrator administrator1 = new Administrator(null);
        Administrator administrator2 = new Administrator(null);
        when(administratorRepository.findAll()).thenReturn(List.of(administrator1, administrator2));

        assertThat(administratorService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(administratorService.getAll()).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        Administrator administrator = new Administrator(null);
        lenient().when(administratorRepository.find(1L)).thenReturn(administrator);

        assertThat(administratorService.get(1L)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> administratorService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Create_IsNotNull() {
        Administrator administrator = new Administrator(null);
        when(administratorRepository.save(any(Administrator.class))).thenReturn(administrator);

        AdministratorProposal administratorProposal = new AdministratorProposal(null);
        assertThat(administratorService.create(administratorProposal)).isNotNull();
    }

    @Test
    void Update_IsNotNull() {
        Administrator administrator = new Administrator(null);
        when(administratorRepository.find(1L)).thenReturn(administrator);
        when(administratorRepository.save(any(Administrator.class))).thenReturn(administrator);

        AdministratorProposal administratorProposal = new AdministratorProposal(null);
        assertThat(administratorService.update(1L, administratorProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        Administrator administrator = new Administrator(null);
        when(administratorRepository.find(1L)).thenReturn(administrator);

        assertDoesNotThrow(() -> administratorService.delete(1L));
    }
}
