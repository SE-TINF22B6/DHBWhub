package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.proposal.PictureProposal;
import de.tinf22b6.dhbwhub.repository.PictureRepository;
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
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PictureServiceImplTests {
    @Mock
    private PictureRepository pictureRepository;

    @InjectMocks
    private PictureServiceImpl pictureService;

    @Test
    void GetAll_HasSize_Two() {
        Picture picture1 = new Picture("profile-img-182310.png", new Byte[]{ 90, 87, 76, 65, 54 });
        Picture picture2 = new Picture("course-img-TINF22B6.jpg", new Byte[]{ 12, 34, 56, 78, 90 });
        when(pictureRepository.findAll()).thenReturn(List.of(picture1, picture2));

        assertThat(pictureService.getAll()).hasSize(2);
    }

    @Test
    void GetAll_IsEmpty() {
        assertThat(pictureService.getAll()).isEmpty();
    }

    @Test
    void Get_IsNotNull() {
        Picture picture = new Picture("profile-img-182310.png", new Byte[]{ 90, 87, 76, 65, 54 });
        lenient().when(pictureRepository.find(1L)).thenReturn(picture);

        assertThat(pictureService.get(1L)).isNotNull();
    }

    @Test
    void Get_ThrowsNoSuchEntryException() {
        NoSuchEntryException e = assertThrows(NoSuchEntryException.class, () -> pictureService.get(1L));
        assertTrue(e.getMessage().matches("(.*) with ID \\d does not exist"));
    }

    @Test
    void Create_IsNotNull() {
        Picture picture = new Picture("profile-img-182310.png", new Byte[]{ 90, 87, 76, 65, 54 });
        when(pictureRepository.save(any(Picture.class))).thenReturn(picture);

        PictureProposal pictureProposal = new PictureProposal("profile-img-182310.png", new Byte[]{ 90, 87, 76, 65, 54 });
        assertThat(pictureService.create(pictureProposal)).isNotNull();
    }

    @Test
    void Update_IsNotNull() {
        Picture picture = new Picture("profile-img-182310.png", new Byte[]{ 90, 87, 76, 65, 54 });
        when(pictureRepository.find(1L)).thenReturn(picture);
        when(pictureRepository.save(any(Picture.class))).thenReturn(picture);

        PictureProposal pictureProposal = new PictureProposal("profile-img-182310.png", new Byte[]{ 90, 87, 76, 65, 54 });
        assertThat(pictureService.update(1L, pictureProposal)).isNotNull();
    }

    @Test
    void Delete_DoesNotThrow() {
        Picture picture = new Picture("profile-img-182310.png", new Byte[]{ 90, 87, 76, 65, 54 });
        when(pictureRepository.find(1L)).thenReturn(picture);

        assertDoesNotThrow(() -> pictureService.delete(1L));
    }
}
