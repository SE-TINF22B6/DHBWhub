package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.AbstractApplicationTest;
import de.tinf22b6.dhbwhub.model.Account;
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
class AccountRepositoryTests extends AbstractApplicationTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    void FindAll_HasSize_Two() {
        Account account1 = createDefaultAccount();
        Account account2 = createDefaultAccount2();

        accountRepository.save(account1);
        accountRepository.save(account2);

        assertThat(accountRepository.findAll()).hasSize(2);
    }

    @Test
    void FindAll_IsEmpty_True() {
        assertThat(accountRepository.findAll()).isEmpty();
    }

    @Test
    void Find_IsNotNull_True() {
        Account account = createDefaultAccount();

        accountRepository.save(account);

        assertThat(accountRepository.find(account.getId())).isNotNull();
    }

    @Test
    void Find_IsNull_True() {
        assertThat(accountRepository.find(1L)).isNull();
    }

    @Test
    void Save_HasSize_One() {
        Account account = createDefaultAccount();

        accountRepository.save(account);

        assertThat(accountRepository.findAll()).hasSize(1);
    }

    @Test
    void Delete_SizeChange() {
        Account account = createDefaultAccount();
        accountRepository.save(account);

        accountRepository.delete(account.getId());

        assertThat(accountRepository.findAll()).isEmpty();
    }
}
