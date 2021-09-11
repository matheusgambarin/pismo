package br.com.pizmo.resource;

import br.com.pizmo.domain.Account;
import br.com.pizmo.exception.DocumentAccountException;
import br.com.pizmo.repository.AccountRepository;
import br.com.pizmo.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountResource.class)
public class AccountResourceTest {

    @MockBean
    private AccountService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addNewAccount() throws Exception {

        Account account = new Account();
        account.setDocumentNumber(12345678900L);

        mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(account)))
                .andExpect(status().isCreated());

        verify(service).addAccount(any(Account.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    public void findAccount() throws Exception {

        Account account = new Account();
        account.setId(1L);
        account.setDocumentNumber(12345678900L);

        when(service.findOne(1L)).thenReturn(account);
        mockMvc.perform(get("/api/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.documentNumber", equalTo(12345678900L)));

        verify(service).findOne(1L);
        verifyNoMoreInteractions(service);
    }
}
