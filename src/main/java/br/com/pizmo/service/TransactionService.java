package br.com.pizmo.service;

import br.com.pizmo.domain.Account;
import br.com.pizmo.domain.Transaction;
import org.springframework.stereotype.Service;
import br.com.pizmo.repository.TransactionRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void addTransaction(Transaction newTransaction) {

        newTransaction.setEventDate(LocalDateTime.now());

        if (newTransaction.getOperation().isDebito()) {
            newTransaction.setAmount(newTransaction.getAmount().negate());
        }

        repository.save(newTransaction);
    }
}
