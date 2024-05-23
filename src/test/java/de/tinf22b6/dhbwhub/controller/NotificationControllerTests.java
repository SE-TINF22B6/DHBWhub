package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.proposal.simplified_models.DeleteNotificationProposal;
import de.tinf22b6.dhbwhub.service.NotificationServiceImpl;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = NotificationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class NotificationControllerTests extends AbstractApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationServiceImpl notificationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GetNotificationsByUser_StatusIsOk() throws Exception {
        when(notificationService.getUserNotifications(1L)).thenReturn(createHomepageNotificationProposals());

        ResultActions response = mockMvc.perform(get("/notification/unseen/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        doNothing().when(notificationService).deleteNotification(any(DeleteNotificationProposal.class));
        DeleteNotificationProposal deleteNotificationProposal = createDeleteNotificationProposal();

        ResultActions response = mockMvc.perform(delete("/notification")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(deleteNotificationProposal)));

        response.andExpect(status().isOk());
    }
}
