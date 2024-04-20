package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.model.Faculty;
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
class FacultyRepositoryTests extends AbstractApplicationTest {
    @Autowired
    private FacultyRepository facultyRepository;

    @Test
    void FindAll_HasSize_Two() {
        Faculty faculty1 = createDefaultFaculty();
        Faculty faculty2 = createDefaultFaculty2();

        facultyRepository.save(faculty1);
        facultyRepository.save(faculty2);

        assertThat(facultyRepository.findAll()).hasSize(2);
    }

    @Test
    void FindAll_IsEmpty_True() {
        assertThat(facultyRepository.findAll()).isEmpty();
    }

    @Test
    void Find_IsNotNull_True() {
        Faculty faculty = createDefaultFaculty();

        facultyRepository.save(faculty);

        assertThat(facultyRepository.find(faculty.getId())).isNotNull();
    }

    @Test
    void Find_IsNull_True() {
        assertThat(facultyRepository.find(1L)).isNull();
    }

    @Test
    void Save_HasSize_One() {
        Faculty faculty = createDefaultFaculty();

        facultyRepository.save(faculty);

        assertThat(facultyRepository.findAll()).hasSize(1);
    }

    @Test
    void Delete_SizeChange() {
        Faculty faculty = createDefaultFaculty();
        facultyRepository.save(faculty);

        facultyRepository.delete(faculty.getId());

        assertThat(facultyRepository.findAll()).isEmpty();
    }
}
