package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.model.*;
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
    void FindAllEventPosts_HasSize_Two() {
        EventPost event1 = createDefaultEventPost();
        EventPost event2 = createDefaultEventPost();

        eventRepository.save(event1);
        eventRepository.save(event2);

        assertThat(eventRepository.findAllEventPosts()).hasSize(2);
    }

    @Test
    void FindAllEventPosts_IsEmpty_True() {
        assertThat(eventRepository.findAllEventPosts()).isEmpty();
    }

/*    @Test
    void FindEventPost_IsNotNull_True() {
        EventPost event = createDefaultEventPost();
        eventRepository.save(event);

        assertThat(eventRepository.findEventPost(1L)).isNotNull();
    }
*/
    @Test
    void FindEventPost_IsNull_True() {
        assertThat(eventRepository.findEventPost(1L)).isNull();
    }
/*
    @Test
    void FindEventComment_IsNotNull_True() {
        EventComment comment = createDefaultEventComment();
        eventRepository.save(comment);

        assertThat(eventRepository.findEventComment(1L)).isNotNull();
    }
*/
    @Test
    void FindEventComment_IsNull_True() {
        assertThat(eventRepository.findEventComment(1L)).isNull();
    }

    @Test
    void FindEventTag_IsNotNull_True() {
        EventTag tag = createDefaultEventTag();
        eventRepository.save(tag);

        assertThat(eventRepository.findEventTag(1L)).isNotNull();
    }

    @Test
    void FindEventTag_IsNull_True() {
        assertThat(eventRepository.findEventTag(1L)).isNull();
    }

    @Test
    void SavePost_HasSize_One() {
        EventPost post = createDefaultEventPost();

        eventRepository.save(post);

        assertThat(eventRepository.findAllEventPosts()).hasSize(1);
    }
}
