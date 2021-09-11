package br.com.pizmo.resource;

import br.com.pizmo.domain.Account;
import br.com.pizmo.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/accounts")
public class AccountResource {

    private final AccountService service;

    public AccountResource(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> addNewAccount(@RequestBody @Valid Account account) {

        service.addAccount(account);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findOne(@PathVariable(name = "id") Long id) {

        return ResponseEntity.ok(service.findOne(id));
    }
}
