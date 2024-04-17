package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.model.SavedPost;
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
class SavedPostRepositoryTests extends AbstractApplicationTest {
    @Autowired
    private SavedPostRepository savedPostRepository;

    @Test
    void FindAll_HasSize_Two() {
        SavedPost savedPost1 = createDefaultSavedPost();
        SavedPost savedPost2 = createDefaultSavedPost2();

        savedPostRepository.save(savedPost1);
        savedPostRepository.save(savedPost2);

        assertThat(savedPostRepository.findAll()).hasSize(2);
    }

    @Test
    void FindAll_IsEmpty_True() {
        assertThat(savedPostRepository.findAll()).isEmpty();
    }

    @Test
    void Find_IsNotNull_True() {
        SavedPost savedPost = createDefaultSavedPost();

        savedPostRepository.save(savedPost);

        assertThat(savedPostRepository.find(savedPost.getId())).isNotNull();
    }

    @Test
    void Find_IsNull_True() {
        assertThat(savedPostRepository.find(1L)).isNull();
    }

    @Test
    void Save_HasSize_One() {
        SavedPost savedPost = createDefaultSavedPost();

        savedPostRepository.save(savedPost);

        assertThat(savedPostRepository.findAll()).hasSize(1);
    }

    @Test
    void Delete_SizeChange() {
        SavedPost savedPost = createDefaultSavedPost();

        savedPostRepository.save(savedPost);

        savedPostRepository.delete(savedPost.getId());

        assertThat(savedPostRepository.findAll()).isEmpty();
    }
}
