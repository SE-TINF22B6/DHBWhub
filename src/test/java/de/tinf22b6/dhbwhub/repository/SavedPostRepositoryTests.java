package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.model.SavedPost;
import de.tinf22b6.dhbwhub.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
@ComponentScan(basePackages = "de.tinf22b6.dhbwhub.repository")
public class SavedPostRepositoryTests {
    @Autowired
    private SavedPostRepository savedPostRepository;

    @Test
    void FindAll_HasSize_Two() {
        SavedPost savedPost1 = new SavedPost(null,null);
        SavedPost savedPost2 = new SavedPost(null,null);

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
        Post post = new Post("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, null, null, null, null);
        User user = new User(19, "Ich studiere Informatik", null, null);

        SavedPost savedPost = new SavedPost(user,post);

        savedPostRepository.save(savedPost);

        assertThat(savedPostRepository.find(savedPost.getId())).isNotNull();
    }

    @Test
    void Find_IsNull_True() {
        assertThat(savedPostRepository.find(1L)).isNull();
    }

    @Test
    void Save_HasSize_One() {
        SavedPost savedPost = new SavedPost(null,null);

        savedPostRepository.save(savedPost);

        assertThat(savedPostRepository.findAll()).hasSize(1);
    }

    @Test
    void Delete_SizeChange() {
        Post post = new Post("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, null, null, null, null);
        User user = new User(19, "Ich studiere Informatik", null, null);

        SavedPost savedPost = new SavedPost(user,post);

        savedPostRepository.save(savedPost);

        savedPostRepository.delete(savedPost.getId());

        assertThat(savedPostRepository.findAll()).isEmpty();
    }
}
