package br.com.pizmo.repository;

import br.com.pizmo.config.BaseConfiguration;
import br.com.pizmo.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@DataJpaTest
@RunWith(SpringRunner.class)
@Import(BaseConfiguration.class)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Test
    public void save() {

        Account account = new Account();
        account.setDocumentNumber(12345678900L);

        Account savedAccount = repository.saveAndFlush(account);
        assertThat(savedAccount.getId()).isNotNull();
    }

    @Test
    @Sql(statements = {"insert into account (id, documentnumber) values (1, 12345678900)"})
    public void findById() {

        Optional<Account> accountOpt = repository.findById(1L);

        assertTrue(accountOpt.isPresent());

        Account account = accountOpt.get();

        assertThat(account.getId()).isEqualTo(1L);
        assertThat(account.getDocumentNumber()).isEqualTo(12345678900L);
    }

    @Test
    @Sql(statements = {"insert into account (id, documentnumber) values (1, 12345678900)"})
    public void existsByDocumentNumber() {

        assertTrue(repository.existsByDocumentNumber(12345678900L));

        assertFalse(repository.existsByDocumentNumber(12345678912L));
    }
}
