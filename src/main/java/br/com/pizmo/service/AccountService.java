package br.com.pizmo.service;

import br.com.pizmo.domain.Account;
import br.com.pizmo.exception.DocumentAccountException;
import org.springframework.stereotype.Service;
import br.com.pizmo.repository.AccountRepository;

import javax.transaction.Transactional;

@Service
public class AccountService {

    public static final String JA_EXISTE_UMA_CONTA_CADASTRADA_COM_ESTE_NUMERO = "Já existe uma conta cadastrada com este número.";
    public static final String NENHUMA_CONTA_ENCONTRADA_COM_ESTE_ID = "Nenhuma conta encontrada com este id: %d.";
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void addAccount(Account account) {

        boolean isAccountExists = repository.existsByDocumentNumber(account.getDocumentNumber());

        if (isAccountExists) {
            throw new DocumentAccountException(JA_EXISTE_UMA_CONTA_CADASTRADA_COM_ESTE_NUMERO);
        }

        repository.save(account);
    }

    public Account findOne(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new DocumentAccountException(String.format(NENHUMA_CONTA_ENCONTRADA_COM_ESTE_ID, id)));
    }
}
