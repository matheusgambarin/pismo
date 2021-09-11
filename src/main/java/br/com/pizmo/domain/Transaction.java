package br.com.pizmo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_generator")
    @SequenceGenerator(name="transaction_generator", sequenceName = "transaction_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(columnDefinition = "account_id")
    private Account account;

    @NotNull
    @ManyToOne
    @JoinColumn(columnDefinition = "operation_id")
    private OperationType operation;

    @NotNull
    private BigDecimal amount;

    @Column(name = "eventdate")
    private LocalDateTime eventDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }
}
