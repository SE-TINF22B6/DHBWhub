package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.model.User;
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
class UserRepositoryTests extends AbstractApplicationTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void FindAll_HasSize_Two() {
        User user1 = createDefaultUser();
        User user2 = createDefaultUser2();

        userRepository.save(user1);
        userRepository.save(user2);

        assertThat(userRepository.findAll()).hasSize(2);
    }

    @Test
    void FindAll_IsEmpty_True() {
        assertThat(userRepository.findAll()).isEmpty();
    }

    @Test
    void Find_IsNotNull_True() {
        User user = createDefaultUser();

        userRepository.save(user);

        assertThat(userRepository.find(user.getId())).isNotNull();
    }

    @Test
    void Find_IsNull_True() {
        assertThat(userRepository.find(1L)).isNull();
    }

    @Test
    void Save_HasSize_One() {
        User user = createDefaultUser();

        userRepository.save(user);

        assertThat(userRepository.findAll()).hasSize(1);
    }

    @Test
    void Delete_SizeChange() {
        User user = createDefaultUser();
        userRepository.save(user);

        userRepository.delete(user.getId());

        assertThat(userRepository.findAll()).isEmpty();
    }
}
