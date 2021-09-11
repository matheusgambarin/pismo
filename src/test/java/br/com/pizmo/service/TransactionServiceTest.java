package br.com.pizmo.service;

import br.com.pizmo.domain.Account;
import br.com.pizmo.domain.OperationType;
import br.com.pizmo.domain.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.pizmo.repository.TransactionRepository;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private TransactionService service;

    @Test
    public void testCompraAVista() {

        Account account = new Account();
        account.setId(1L);
        BigDecimal amount = new BigDecimal("25.80");
        OperationType operation = new OperationType();
        operation.setId(1L);

        Transaction novaTransacao = new Transaction();
        novaTransacao.setAmount(amount);
        novaTransacao.setOperation(operation);
        novaTransacao.setAccount(account);

        service.addTransaction(novaTransacao);

        assertThat(novaTransacao.getEventDate()).isNotNull();
        assertThat(novaTransacao.getAmount()).isEqualTo(new BigDecimal("-25.80"));
        verify(repository).save(novaTransacao);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void testCompraParcelada() {

        Account account = new Account();
        account.setId(1L);
        BigDecimal amount = new BigDecimal("25.80");
        OperationType operation = new OperationType();
        operation.setId(2L);

        Transaction novaTransacao = new Transaction();
        novaTransacao.setAmount(amount);
        novaTransacao.setOperation(operation);
        novaTransacao.setAccount(account);

        service.addTransaction(novaTransacao);

        assertThat(novaTransacao.getEventDate()).isNotNull();
        assertThat(novaTransacao.getAmount()).isEqualTo(new BigDecimal("-25.80"));
        verify(repository).save(novaTransacao);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void testSaque() {

        Account account = new Account();
        account.setId(1L);
        BigDecimal amount = new BigDecimal("25.80");
        OperationType operation = new OperationType();
        operation.setId(3L);

        Transaction novaTransacao = new Transaction();
        novaTransacao.setAmount(amount);
        novaTransacao.setOperation(operation);
        novaTransacao.setAccount(account);

        service.addTransaction(novaTransacao);

        assertThat(novaTransacao.getEventDate()).isNotNull();
        assertThat(novaTransacao.getAmount()).isEqualTo(new BigDecimal("-25.80"));
        verify(repository).save(novaTransacao);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void testPagamento() {

        Account account = new Account();
        account.setId(1L);
        BigDecimal amount = new BigDecimal("25.80");
        OperationType operation = new OperationType();
        operation.setId(4L);

        Transaction novaTransacao = new Transaction();
        novaTransacao.setAmount(amount);
        novaTransacao.setOperation(operation);
        novaTransacao.setAccount(account);

        service.addTransaction(novaTransacao);

        assertThat(novaTransacao.getEventDate()).isNotNull();
        assertThat(novaTransacao.getAmount()).isEqualTo(amount);
        verify(repository).save(novaTransacao);
        verifyNoMoreInteractions(repository);
    }

}
