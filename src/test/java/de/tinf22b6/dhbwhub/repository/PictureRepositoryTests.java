package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.model.User;
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
class PictureRepositoryTests extends AbstractApplicationTest {
    @Autowired
    private PictureRepository pictureRepository;

    @Autowired UserRepository userRepository;

    @Test
    void FindAll_HasSize_Two() {
        Picture picture1 = createDefaultPicture();
        Picture picture2 = createDefaultPicture2();

        pictureRepository.save(picture1);
        pictureRepository.save(picture2);

        assertThat(pictureRepository.findAll()).hasSize(2);
    }

    @Test
    void FindAll_IsEmpty_True() {
        assertThat(pictureRepository.findAll()).isEmpty();
    }

    @Test
    void Find_IsNotNull_True() {
        Picture picture = createDefaultPicture();

        pictureRepository.save(picture);

        assertThat(pictureRepository.find(picture.getId())).isNotNull();
    }

    @Test
    void Find_IsNull_True() {
        assertThat(pictureRepository.find(1L)).isNull();
    }

    @Test
    void FindByUserId_IsNotNull_True() {
        User user = createDefaultUser();

        userRepository.save(user);

        assertThat(pictureRepository.findByUserId(user.getId())).isNotNull();
    }

    @Test
    void FindByUserId_IsNull_True() {
        assertThat(pictureRepository.findByUserId(1L)).isNull();
    }

    @Test
    void Save_HasSize_One() {
        Picture picture = createDefaultPicture();

        pictureRepository.save(picture);

        assertThat(pictureRepository.findAll()).hasSize(1);
    }

    @Test
    void Delete_SizeChange() {
        Picture picture = createDefaultPicture();
        pictureRepository.save(picture);

        pictureRepository.delete(picture.getId());

        assertThat(pictureRepository.findAll()).isEmpty();
    }
}
