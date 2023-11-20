package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Comment;
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
public class CommentRepositoryTests {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void FindAll_HasSize_Two() {
        Comment comment1 = new Comment("Das ist ganz normaler Kommentar", new Date(1478979207L), 4, null, null, null);
        Comment comment2 = new Comment("Du mieser Hund!", new Date(1478979183L), 5, null, null, null);

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        assertThat(commentRepository.findAll()).hasSize(2);
    }

    @Test
    void FindAll_IsEmpty_True() {
        assertThat(commentRepository.findAll()).isEmpty();
    }

    @Test
    void Find_IsNotNull_True() {
        Comment comment = new Comment("Das ist ganz normaler Kommentar", new Date(1478979207L), 4, null, null, null);

        commentRepository.save(comment);

        assertThat(commentRepository.find(comment.getId())).isNotNull();
    }

    @Test
    void Find_IsNull_True() {
        assertThat(commentRepository.find(1L)).isNull();
    }

    @Test
    void Save_HasSize_One() {
        Comment comment = new Comment("Das ist ganz normaler Kommentar", new Date(1478979207L), 4, null, null, null);

        commentRepository.save(comment);

        assertThat(commentRepository.findAll()).hasSize(1);
    }

    @Test
    void Delete_SizeChange() {
        Comment comment = new Comment("Das ist ganz normaler Kommentar", new Date(1478979207L), 4, null, null, null);
        commentRepository.save(comment);

        commentRepository.delete(comment.getId());

        assertThat(commentRepository.findAll()).isEmpty();
    }
}
