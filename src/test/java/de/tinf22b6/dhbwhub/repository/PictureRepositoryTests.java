package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Picture;
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
public class PictureRepositoryTests {
    @Autowired
    private PictureRepository pictureRepository;

    @Test
    void FindAll_HasSize_Two() {
        // Arrange
        Picture picture1 = new Picture("profile-img-182310.png", new Byte[]{ 90, 87, 76, 65, 54 });
        Picture picture2 = new Picture("course-img-TINF22B6.jpg", new Byte[]{ 12, 34, 56, 78, 90 });

        // Act
        pictureRepository.save(picture1);
        pictureRepository.save(picture2);

        // Assert
        assertThat(pictureRepository.findAll()).hasSize(2);
    }

    @Test
    void FindAll_IsEmpty_True() {
        assertThat(pictureRepository.findAll()).isEmpty();
    }

    @Test
    void Find_IsNotNull_True() {
        Picture picture = new Picture("profile-img-182310.png", new Byte[]{ 90, 87, 76, 65, 54 });

        pictureRepository.save(picture);

        assertThat(pictureRepository.find(picture.getId())).isNotNull();
    }

    @Test
    void Find_IsNull_True() {
        assertThat(pictureRepository.find(1L)).isNull();
    }

    @Test
    void Save_HasSize_One() {
        Picture picture = new Picture("profile-img-182310.png", new Byte[]{ 90, 87, 76, 65, 54 });

        pictureRepository.save(picture);

        assertThat(pictureRepository.findAll()).hasSize(1);
    }

    @Test
    void Delete_SizeChange() {
        Picture picture = new Picture("profile-img-182310.png", new Byte[]{ 90, 87, 76, 65, 54 });

        pictureRepository.save(picture);

        assertThat(pictureRepository.findAll()).hasSize(1);

        pictureRepository.delete(picture.getId());

        assertThat(pictureRepository.findAll()).isEmpty();
    }
}
