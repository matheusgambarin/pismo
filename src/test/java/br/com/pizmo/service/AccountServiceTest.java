package br.com.pizmo.service;

import br.com.pizmo.domain.Account;
import br.com.pizmo.exception.DocumentAccountException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.pizmo.repository.AccountRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountService service;

    @Test
    public void addAccount() {

        when(repository.existsByDocumentNumber(12345678900L)).thenReturn(false);

        Account account = new Account();
        account.setDocumentNumber(12345678900L);
        service.addAccount(account);

        verify(repository).existsByDocumentNumber(12345678900L);
        verify(repository).save(account);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void addAccountSameDocumentNumberException() {

        when(repository.existsByDocumentNumber(12345678900L)).thenReturn(true);

        assertThatThrownBy(() -> {
            Account newAccount = new Account();
            newAccount.setDocumentNumber(12345678900L);
            service.addAccount(newAccount);
        })
                .isInstanceOf(DocumentAccountException.class)
                .hasMessage("Já existe uma conta cadastrada com este número.");

        verify(repository).existsByDocumentNumber(12345678900L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void getAccount() {

        Account existingAccount = new Account();
        existingAccount.setId(1L);
        existingAccount.setDocumentNumber(12345678900L);

        when(repository.findById(1L)).thenReturn(Optional.of(existingAccount));
        Account account = service.findOne(1L);

        assertThat(account.getId()).isEqualTo(1L);
        assertThat(account.getDocumentNumber()).isEqualTo(12345678900L);

        verify(repository).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void getAccountException() {

        Account existingAccount = new Account();
        existingAccount.setId(1L);
        existingAccount.setDocumentNumber(12345678900L);
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            service.findOne(1L);
        })
                .isInstanceOf(DocumentAccountException.class)
                .hasMessage("Nenhuma conta encontrada com este id: 1.");

        verify(repository).findById(1L);
        verifyNoMoreInteractions(repository);
    }
}
