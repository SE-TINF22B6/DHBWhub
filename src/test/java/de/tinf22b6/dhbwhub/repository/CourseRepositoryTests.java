package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
@ComponentScan(basePackages = "de.tinf22b6.dhbwhub.repository")
class CourseRepositoryTests extends AbstractApplicationTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void FindAll_HasSize_Two() {
        Course course1 = createDefaultCourse();
        Course course2 = createDefaultCourse2();

        courseRepository.save(course1);
        courseRepository.save(course2);

        assertThat(courseRepository.findAll()).hasSize(2);
    }

    @Test
    void FindAll_IsEmpty_True() {
        assertThat(courseRepository.findAll()).isEmpty();
    }

    @Test
    void Find_IsNotNull_True() {
        Course course = createDefaultCourse();

        courseRepository.save(course);

        assertThat(courseRepository.find(course.getId())).isNotNull();
    }

    @Test
    void Find_IsNull_True() {
        assertThat(courseRepository.find(1L)).isNull();
    }

    @Test
    void Save_HasSize_One() {
        Course course = createDefaultCourse();

        courseRepository.save(course);

        assertThat(courseRepository.findAll()).hasSize(1);
    }

    @Test
    void Delete_SizeChange() {
        Course course = createDefaultCourse();
        courseRepository.save(course);

        courseRepository.delete(course.getId());

        assertThat(courseRepository.findAll()).isEmpty();
    }
}
