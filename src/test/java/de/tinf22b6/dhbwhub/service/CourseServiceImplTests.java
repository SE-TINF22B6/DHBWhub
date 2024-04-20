package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Course;
import de.tinf22b6.dhbwhub.proposal.CourseProposal;
import de.tinf22b6.dhbwhub.repository.CourseRepository;
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
class CourseServiceImplTests extends AbstractApplicationTest {
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void GetAll_HasSize_Two() {
        Course course1 = createDefaultCourse();
        Course course2 = createDefaultCourse2();
        when(courseRepository.findAll()).thenReturn(List.of(course1, course2));

        assertThat(courseService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(courseService.getAll()).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        Course course = createDefaultCourse();
        when(courseRepository.find(1L)).thenReturn(course);

        assertThat(courseService.get(1L)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> courseService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Create_IsNotNull() {
        Course course = createDefaultCourse();
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        CourseProposal courseProposal = createDefaultCourseProposal();
        assertThat(courseService.create(courseProposal)).isNotNull();
    }

    @Test
    void Update_IsNotNull() {
        Course course = createDefaultCourse();
        when(courseRepository.find(1L)).thenReturn(course);
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        CourseProposal courseProposal = createDefaultCourseProposal();
        assertThat(courseService.update(1L, courseProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        Course course = createDefaultCourse();
        when(courseRepository.find(1L)).thenReturn(course);

        assertDoesNotThrow(() -> courseService.delete(1L));
    }
}
