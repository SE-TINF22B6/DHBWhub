package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Faculty;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringFacultyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
public class FacultyRepositoryTests {
    @Autowired
    private SpringFacultyRepository facultyRepository;

    @Test
    void TestFindAll() {
        // Arrange
        Faculty faculty = new Faculty("TINF22B6");

        // Act
        facultyRepository.save(faculty);

        // Assert
        assertThat(facultyRepository.findAll()).isNotEmpty();
    }
}
