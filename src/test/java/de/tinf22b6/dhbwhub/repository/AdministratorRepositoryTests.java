package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Administrator;
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
public class AdministratorRepositoryTests {
    @Autowired
    private AdministratorRepository administratorRepository;

    @Test
    void FindAll_HasSize_Two() {
        // Arrange
        Administrator administrator1 = new Administrator(null);
        Administrator administrator2 = new Administrator(null);

        // Act
        administratorRepository.save(administrator1);
        administratorRepository.save(administrator2);

        // Assert
        assertThat(administratorRepository.findAll()).hasSize(2);
    }

    @Test
    void FindAll_IsEmpty_True() {
        assertThat(administratorRepository.findAll()).isEmpty();
    }

    @Test
    void Find_IsNotNull_True() {
        Administrator administrator = new Administrator(null);

        administratorRepository.save(administrator);

        assertThat(administratorRepository.find(administrator.getId())).isNotNull();
    }

    @Test
    void Find_IsNull_True() {
        assertThat(administratorRepository.find(1L)).isNull();
    }

    @Test
    void Save_HasSize_One() {
        Administrator administrator = new Administrator(null);

        administratorRepository.save(administrator);

        assertThat(administratorRepository.findAll()).hasSize(1);
    }

    @Test
    void Delete_SizeChange() {
        Administrator administrator = new Administrator(null);

        administratorRepository.save(administrator);

        assertThat(administratorRepository.findAll()).hasSize(1);

        administratorRepository.delete(administrator.getId());

        assertThat(administratorRepository.findAll()).isEmpty();
    }
}
