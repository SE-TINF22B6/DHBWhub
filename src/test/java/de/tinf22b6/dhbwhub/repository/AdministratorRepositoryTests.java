package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
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
class AdministratorRepositoryTests extends AbstractApplicationTest {
    @Autowired
    private AdministratorRepository administratorRepository;

    @Test
    void FindAll_HasSize_Two() {
        Administrator administrator1 = createDefaultAdministrator();
        Administrator administrator2 = createDefaultAdministrator2();

        administratorRepository.save(administrator1);
        administratorRepository.save(administrator2);

        assertThat(administratorRepository.findAll()).hasSize(2);
    }

    @Test
    void FindAll_IsEmpty_True() {
        assertThat(administratorRepository.findAll()).isEmpty();
    }

    @Test
    void Find_IsNotNull_True() {
        Administrator administrator = createDefaultAdministrator();

        administratorRepository.save(administrator);

        assertThat(administratorRepository.find(administrator.getId())).isNotNull();
    }

    @Test
    void Find_IsNull_True() {
        assertThat(administratorRepository.find(1L)).isNull();
    }

    @Test
    void Save_HasSize_One() {
        Administrator administrator = createDefaultAdministrator();

        administratorRepository.save(administrator);

        assertThat(administratorRepository.findAll()).hasSize(1);
    }

    @Test
    void Delete_SizeChange() {
        Administrator administrator = createDefaultAdministrator();
        administratorRepository.save(administrator);

        administratorRepository.delete(administrator.getId());

        assertThat(administratorRepository.findAll()).isEmpty();
    }
}
