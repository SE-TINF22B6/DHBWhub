package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.model.Event;
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
class EventRepositoryTests extends AbstractApplicationTest {
    @Autowired
    private EventRepository eventRepository;

    @Test
    void FindAll_HasSize_Two() {
        Event event1 = createDefaultEvent();
        Event event2 = createDefaultEvent2();

        eventRepository.save(event1);
        eventRepository.save(event2);

        assertThat(eventRepository.findAll()).hasSize(2);
    }

    @Test
    void FindAll_IsEmpty_True() {
        assertThat(eventRepository.findAll()).isEmpty();
    }

    @Test
    void Find_IsNotNull_True() {
        Event event = createDefaultEvent();

        eventRepository.save(event);

        assertThat(eventRepository.find(event.getId())).isNotNull();
    }

    @Test
    void Find_IsNull_True() {
        assertThat(eventRepository.find(1L)).isNull();
    }

    @Test
    void Save_HasSize_One() {
        Event event = createDefaultEvent();

        eventRepository.save(event);

        assertThat(eventRepository.findAll()).hasSize(1);
    }

    @Test
    void Delete_SizeChange() {
        Event event = createDefaultEvent();
        eventRepository.save(event);

        eventRepository.delete(event.getId());

        assertThat(eventRepository.findAll()).isEmpty();
    }
}
