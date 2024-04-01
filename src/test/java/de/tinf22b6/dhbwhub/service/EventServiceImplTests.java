package de.tinf22b6.dhbwhub.service;

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

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class EventServiceImplTests {
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @Test
    void GetAll_HasSize_Two() {
        Event event1 = new Event("Master-Event-1",new Date(1701242553L));
        Event event2 = new Event("Master-Event-2",new Date(1701242512L));

        when(eventRepository.findAll()).thenReturn(List.of(event1, event2));

        assertThat(eventService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(eventService.getAll()).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        Event event = new Event("Master-Event",new Date(1701242553L));
        lenient().when(eventRepository.find(1L)).thenReturn(event);

        assertThat(eventService.get(1L)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> eventService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Create_IsNotNull() {
        Event event = new Event("Master-Event",new Date(1701242553L));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        EventProposal eventProposal = new EventProposal("Master-Event",new Date(1701242553L));
        assertThat(eventService.create(eventProposal)).isNotNull();
    }

    @Test
    void Update_IsNotNull() {
        Event event = new Event("Master-Event",new Date(1701242553L));
        when(eventRepository.find(1L)).thenReturn(event);
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        EventProposal eventProposal = new EventProposal("Master-Event",new Date(1701242553L));
        assertThat(eventService.update(1L, eventProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        Event event = new Event("Master-Event",new Date(1701242553L));
        when(eventRepository.find(1L)).thenReturn(event);

        assertDoesNotThrow(() -> eventService.delete(1L));
    }
}
