package de.tinf22b6.dhbwhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tinf22b6.dhbwhub.mapper.PictureMapper;
import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.proposal.PictureProposal;
import de.tinf22b6.dhbwhub.service.PictureServiceImpl;
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

@WebMvcTest(controllers = PictureController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PictureControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PictureServiceImpl pictureService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GetAll_StatusIsOk() throws Exception {
        Picture picture = new Picture("profile-img-182310.png", new Byte[]{ 90, 87, 76, 65, 54 });
        when(pictureService.getAll()).thenReturn(List.of(picture, picture));

        ResultActions response = mockMvc.perform(get("/picture")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void Get_StatusIsOk() throws Exception {
        Picture picture = new Picture("profile-img-182310.png", new Byte[]{ 90, 87, 76, 65, 54 });
        when(pictureService.get(any(Long.class))).thenReturn(picture);

        ResultActions response = mockMvc.perform(get("/picture/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(picture.getName())))
                .andExpect(jsonPath("$.imageData").isArray())
                .andExpect(jsonPath("$.imageData", hasSize(picture.getImageData().length)));
    }

    @Test
    void Create_StatusIsOk() throws Exception {
        PictureProposal pictureProposal = new PictureProposal("profile-img-182310.png", new Byte[]{ 90, 87, 76, 65, 54 });
        given(pictureService.create(any(PictureProposal.class))).willAnswer(i -> PictureMapper.mapToModel(i.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/picture")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pictureProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(pictureProposal.getName())))
                .andExpect(jsonPath("$.imageData").isArray())
                .andExpect(jsonPath("$.imageData", hasSize(pictureProposal.getImageData().length)));
    }

    @Test
    void Update_StatusIsOk() throws Exception {
        PictureProposal pictureProposal = new PictureProposal("profile-img-182310.png", new Byte[]{ 90, 87, 76, 65, 54 });
        when(pictureService.update(any(Long.class), any(PictureProposal.class))).thenReturn(PictureMapper.mapToModel(pictureProposal));

        ResultActions response = mockMvc.perform(put("/picture/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pictureProposal)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(pictureProposal.getName())))
                .andExpect(jsonPath("$.imageData").isArray())
                .andExpect(jsonPath("$.imageData", hasSize(pictureProposal.getImageData().length)));
    }

    @Test
    void Delete_StatusIsOk() throws Exception {
        doNothing().when(pictureService).delete(any(Long.class));

        ResultActions response = mockMvc.perform(delete("/picture/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }
}
