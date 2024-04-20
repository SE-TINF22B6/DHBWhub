package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.mapper.CourseMapper;
import de.tinf22b6.dhbwhub.model.Course;
import de.tinf22b6.dhbwhub.proposal.CourseProposal;
import de.tinf22b6.dhbwhub.service.CourseServiceImpl;
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

@WebMvcTest(controllers = CourseController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class CourseControllerTests extends AbstractApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseServiceImpl courseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GetAll_StatusIsOk() throws Exception {
        Course course = createDefaultCourse();
        when(courseService.getAll()).thenReturn(List.of(course, course));

        ResultActions response = mockMvc.perform(get("/course")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void Get_StatusIsOk() throws Exception {
        Course course = createDefaultCourse();
        when(courseService.get(any(Long.class))).thenReturn(course);

        ResultActions response = mockMvc.perform(get("/course/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(course.getName())));
    }

    @Test
    void Create_StatusIsOk() throws Exception {
        CourseProposal courseProposal = createDefaultCourseProposal();
        given(courseService.create(any(CourseProposal.class))).willAnswer(i -> CourseMapper.mapToModel(i.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/course")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(courseProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(courseProposal.getName())));
    }

    @Test
    void Update_StatusIsOk() throws Exception {
        CourseProposal courseProposal = createDefaultCourseProposal();
        when(courseService.update(any(Long.class), any(CourseProposal.class))).thenReturn(CourseMapper.mapToModel(courseProposal));

        ResultActions response = mockMvc.perform(put("/course/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(courseProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(courseProposal.getName())));
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        doNothing().when(courseService).delete(any(Long.class));

        ResultActions response = mockMvc.perform(delete("/course/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }
}
