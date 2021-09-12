package br.com.pizmo.repository;

import br.com.pizmo.config.BaseConfiguration;
import br.com.pizmo.domain.Account;
import br.com.pizmo.domain.OperationType;
import br.com.pizmo.domain.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@Import(BaseConfiguration.class)
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository repository;

    @Test
    public void save() {

        Transaction transaction = new Transaction();
        transaction.setEventDate(LocalDateTime.now());
        transaction.setAmount(new BigDecimal("25.80"));

        OperationType operation = new OperationType();
        operation.setId(1L);
        transaction.setOperation(operation);

        Account account = new Account();
        account.setId(1L);
        transaction.setAccount(account);
        Transaction savedTransaction = repository.saveAndFlush(transaction);
        assertThat(savedTransaction.getId()).isNotNull();
    }
}
