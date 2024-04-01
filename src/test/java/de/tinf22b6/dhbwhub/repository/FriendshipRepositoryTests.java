package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.Friendship;
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
public class FriendshipRepositoryTests {
    @Autowired
    private FriendshipRepository friendshipRepository;

    @Test
    void FindAll_HasSize_Two() {
        Friendship friendship1 = new Friendship(null,null, false);
        Friendship friendship2 = new Friendship(null,null, false);


        friendshipRepository.save(friendship1);
        friendshipRepository.save(friendship2);

        assertThat(friendshipRepository.findAll()).hasSize(2);
    }

    @Test
    void FindAll_IsEmpty_True() {
        assertThat(friendshipRepository.findAll()).isEmpty();
    }

    @Test
    void Find_IsNotNull_True() {
        Friendship friendship = new Friendship(null,null, false);

        friendshipRepository.save(friendship);

        assertThat(friendshipRepository.find(friendship.getId())).isNotNull();
    }

    @Test
    void Find_IsNull_True() {
        assertThat(friendshipRepository.find(1L)).isNull();
    }

    @Test
    void Save_HasSize_One() {
        Friendship friendship = new Friendship(null,null, false);

        friendshipRepository.save(friendship);

        assertThat(friendshipRepository.findAll()).hasSize(1);
    }

    @Test
    void Delete_SizeChange() {
        Friendship friendship = new Friendship(null,null, false);

        friendshipRepository.save(friendship);

        friendshipRepository.delete(friendship.getId());

        assertThat(friendshipRepository.findAll()).isEmpty();
    }
}
