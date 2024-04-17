package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Event;
import de.tinf22b6.dhbwhub.proposal.EventProposal;
import de.tinf22b6.dhbwhub.repository.EventRepository;
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
class EventServiceImplTests extends AbstractApplicationTest {
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @Test
    void GetAll_HasSize_Two() {
        Event event1 = createDefaultEvent();
        Event event2 = createDefaultEvent2();

        when(eventRepository.findAll()).thenReturn(List.of(event1, event2));

        assertThat(eventService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(eventService.getAll()).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        Event event = createDefaultEvent();
        when(eventRepository.find(1L)).thenReturn(event);

        assertThat(eventService.get(1L)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> eventService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Create_IsNotNull() {
        Event event = createDefaultEvent();
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        EventProposal eventProposal = createDefaultEventProposal();
        assertThat(eventService.create(eventProposal)).isNotNull();
    }

    @Test
    void Update_IsNotNull() {
        Event event = createDefaultEvent();
        when(eventRepository.find(1L)).thenReturn(event);
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        EventProposal eventProposal = createDefaultEventProposal();
        assertThat(eventService.update(1L, eventProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        Event event = createDefaultEvent();
        when(eventRepository.find(1L)).thenReturn(event);

        assertDoesNotThrow(() -> eventService.delete(1L));
    }
}
