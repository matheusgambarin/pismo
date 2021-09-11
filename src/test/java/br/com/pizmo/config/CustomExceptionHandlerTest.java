package br.com.pizmo.config;

import br.com.pizmo.exception.DocumentAccountException;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class CustomExceptionHandlerTest {

    @Test
    public void handleDocumentAccountException() {

        CustomExceptionHandler handler = new CustomExceptionHandler();
        ServletWebRequest servletWebRequest = new ServletWebRequest(new MockHttpServletRequest());
        ResponseEntity<Object> objectResponseEntity = handler.handleDocumentAccountException(new DocumentAccountException("Já existe uma conta com este id: 1."), servletWebRequest);

        assertThat(objectResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(objectResponseEntity.getBody()).isEqualTo("Já existe uma conta com este id: 1.");
    }

    @Test
    public void handleMethodArgumentNotValid() {

        CustomExceptionHandler handler = new CustomExceptionHandler();
        ServletWebRequest servletWebRequest = new ServletWebRequest(new MockHttpServletRequest());
        MethodParameter parameter = Mockito.mock(MethodParameter.class);
        BindingResult binding = Mockito.mock(BindingResult.class);

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(parameter, binding);

        HttpHeaders httpHeaders = new HttpHeaders();

        FieldError fieldError = new FieldError("Dummy", "DummyField", "Dummy is required");
        when(binding.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));
        ResponseEntity<Object> objectResponseEntity = handler.handleMethodArgumentNotValid(ex, httpHeaders, HttpStatus.BAD_REQUEST, servletWebRequest);
        assertThat(objectResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        CustomExceptionHandler.Error customError = (CustomExceptionHandler.Error) objectResponseEntity.getBody();

        assertThat(customError).isNotNull();
        assertThat(customError.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(customError.getFieldErrors())
                .extracting(FieldError::getField).contains("DummyField");
    }
}