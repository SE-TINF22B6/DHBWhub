package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.mapper.FacultyMapper;
import de.tinf22b6.dhbwhub.model.Faculty;
import de.tinf22b6.dhbwhub.proposal.FacultyProposal;
import de.tinf22b6.dhbwhub.service.FacultyServiceImpl;
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

@WebMvcTest(controllers = FacultyController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class FacultyControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyServiceImpl facultyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GetAll_StatusIsOk() throws Exception {
        Faculty faculty = new Faculty("Informatik");
        when(facultyService.getAll()).thenReturn(List.of(faculty, faculty));

        ResultActions response = mockMvc.perform(get("/faculty")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void Get_StatusIsOk() throws Exception {
        Faculty faculty = new Faculty("Informatik");
        when(facultyService.get(any(Long.class))).thenReturn(faculty);

        ResultActions response = mockMvc.perform(get("/faculty/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(faculty.getName())));
    }

    @Test
    void Create_StatusIsOk() throws Exception {
        FacultyProposal facultyProposal = new FacultyProposal("Informatik");
        given(facultyService.create(any(FacultyProposal.class))).willAnswer(i -> FacultyMapper.mapToModel(i.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/faculty")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(facultyProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(facultyProposal.getName())));
    }

    @Test
    void Update_StatusIsOk() throws Exception {
        FacultyProposal facultyProposal = new FacultyProposal("Informatik");
        when(facultyService.update(any(Long.class), any(FacultyProposal.class))).thenReturn(FacultyMapper.mapToModel(facultyProposal));

        ResultActions response = mockMvc.perform(put("/faculty/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(facultyProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(facultyProposal.getName())));
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        doNothing().when(facultyService).delete(any(Long.class));

        ResultActions response = mockMvc.perform(delete("/faculty/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }
}
