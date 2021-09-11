package br.com.pizmo.resource;


import br.com.pizmo.domain.Transaction;
import br.com.pizmo.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
public class TransactionResource {

    private final TransactionService service;

    public TransactionResource(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> addNewTransaction(@RequestBody @Valid Transaction transaction) {

        service.addTransaction(transaction);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
