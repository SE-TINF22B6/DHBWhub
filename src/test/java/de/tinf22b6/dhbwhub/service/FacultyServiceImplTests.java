package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Faculty;
import de.tinf22b6.dhbwhub.proposal.FacultyProposal;
import de.tinf22b6.dhbwhub.repository.FacultyRepository;
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
class FacultyServiceImplTests extends AbstractApplicationTest {
    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private FacultyServiceImpl facultyService;

    @Test
    void GetAll_HasSize_Two() {
        Faculty faculty1 = createDefaultFaculty();
        Faculty faculty2 = createDefaultFaculty2();
        when(facultyRepository.findAll()).thenReturn(List.of(faculty1, faculty2));

        assertThat(facultyService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(facultyService.getAll()).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        Faculty faculty = createDefaultFaculty();
        when(facultyRepository.find(1L)).thenReturn(faculty);

        assertThat(facultyService.get(1L)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> facultyService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Create_IsNotNull() {
        Faculty faculty = createDefaultFaculty();
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        FacultyProposal facultyProposal = createDefaultFacultyProposal();
        assertThat(facultyService.create(facultyProposal)).isNotNull();
    }

    @Test
    void Update_IsNotNull() {
        Faculty faculty = createDefaultFaculty();
        when(facultyRepository.find(1L)).thenReturn(faculty);
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        FacultyProposal facultyProposal = createDefaultFacultyProposal();
        assertThat(facultyService.update(1L, facultyProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        Faculty faculty = createDefaultFaculty();
        when(facultyRepository.find(1L)).thenReturn(faculty);

        assertDoesNotThrow(() -> facultyService.delete(1L));
    }
}
