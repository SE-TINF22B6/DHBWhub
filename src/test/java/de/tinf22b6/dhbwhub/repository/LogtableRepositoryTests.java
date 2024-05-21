package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
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
class LogtableRepositoryTests extends AbstractApplicationTest {
    @Autowired
    private LogtableRepository logtableRepository;

    @Test
    void GetAllPosts_HasSize_One() {
        logtableRepository.savePost(createLikeLogtablePost());
        assertThat(logtableRepository.findAllPosts()).hasSize(1);
    }

    @Test
    void GetAllComments_HasSize_One() {
        logtableRepository.saveComment(createLikeLogtablePostComment());
        assertThat(logtableRepository.findAllComments()).hasSize(1);
    }

    @Test
    void GetAllEvents_HasSize_One() {
        logtableRepository.saveEvent(createLikeLogtableEventPost());
        assertThat(logtableRepository.findAllEvents()).hasSize(1);
    }

    @Test
    void GetAllEventComments_HasSize_One() {
        logtableRepository.saveEventComment(createLikeLogtableEventComment());
        assertThat(logtableRepository.findAllEventComments()).hasSize(1);
    }
}
