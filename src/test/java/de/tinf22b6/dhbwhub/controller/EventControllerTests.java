package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.mapper.EventMapper;
import de.tinf22b6.dhbwhub.model.Event;
import de.tinf22b6.dhbwhub.proposal.EventProposal;
import de.tinf22b6.dhbwhub.service.EventServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = EventController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class EventControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventServiceImpl eventService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GetAll_StatusIsOk() throws Exception {
        Event event = new Event("Master-Event",new Date(1701242553L));
        when(eventService.getAll()).thenReturn(List.of(event, event));

        ResultActions response = mockMvc.perform(get("/event")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void Get_StatusIsOk() throws Exception {
        Event event = new Event("Master-Event",new Date(1701242553L));
        when(eventService.get(any(Long.class))).thenReturn(event);

        ResultActions response = mockMvc.perform(get("/event/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(event.getName())));
    }

    @Test
    void Create_StatusIsOk() throws Exception {
        EventProposal eventProposal = new EventProposal("Master-Event",new Date(1701242553L));
        given(eventService.create(any(EventProposal.class))).willAnswer(i -> EventMapper.mapToModel(i.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(eventProposal.getName())));
    }

    @Test
    void Update_StatusIsOk() throws Exception {
        EventProposal eventProposal = new EventProposal("Master-Event",new Date(1701242553L));
        when(eventService.update(any(Long.class), any(EventProposal.class))).thenReturn(EventMapper.mapToModel(eventProposal));

        ResultActions response = mockMvc.perform(put("/event/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(eventProposal.getName())));
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        doNothing().when(eventService).delete(any(Long.class));

        ResultActions response = mockMvc.perform(delete("/event/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }
}