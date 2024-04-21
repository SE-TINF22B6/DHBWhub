package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
@ComponentScan(basePackages = "de.tinf22b6.dhbwhub.repository")
class PostRepositoryTests extends AbstractApplicationTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    void FindAll_HasSize_Two() {
        Post post1 = createDefaultPost();
        Post post2 = createDefaultPost2();

        postRepository.save(post1);
        postRepository.save(post2);

        assertThat(postRepository.findAll()).hasSize(2);
    }

    @Test
    void FindAll_IsEmpty_True() {
        assertThat(postRepository.findAll()).isEmpty();
    }

    @Test
    void Find_IsNotNull_True() {
        Post post = createDefaultPost();

        postRepository.save(post);

        assertThat(postRepository.find(post.getId())).isNotNull();
    }

    @Test
    void Find_IsNull_True() {
        assertThat(postRepository.find(1L)).isNull();
    }

    @Test
    void Save_HasSize_One() {
        Post post = createDefaultPost();

        postRepository.save(post);

        assertThat(postRepository.findAll()).hasSize(1);
    }

    @Test
    void Delete_SizeChange() {
        Post post = createDefaultPost();
        postRepository.save(post);

        postRepository.delete(post.getId());

        assertThat(postRepository.findAll()).isEmpty();
    }
}
