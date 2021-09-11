package br.com.pizmo.resource;

import br.com.pizmo.domain.Account;
import br.com.pizmo.domain.OperationType;
import br.com.pizmo.domain.Transaction;
import br.com.pizmo.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionResource.class)
public class TransactionResourceTest {

    @MockBean
    private TransactionService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addNewTransaction() throws Exception {

        Transaction transaction = new Transaction();
        transaction.setAccount(new Account());
        transaction.setAmount(new BigDecimal("25.80"));
        OperationType operation = new OperationType();
        operation.setId(1L);
        transaction.setOperation(operation);

        mockMvc.perform(post("/api/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(transaction)))
                .andExpect(status().isCreated());

        verify(service).addTransaction(any(Transaction.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    public void addNewTransactionMethodArgumentException() throws Exception {

        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal("25.80"));

        mockMvc.perform(post("/api/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(transaction)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[*].field", hasItems("operation", "account")));
    }
}
