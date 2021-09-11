package br.com.pizmo.config;

import br.com.pizmo.exception.DocumentAccountException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DocumentAccountException.class})
    protected ResponseEntity<Object> handleDocumentAccountException(DocumentAccountException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return ResponseEntity.badRequest().body(processFieldErrors(fieldErrors));
    }

    private Error processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        Error error = new Error(HttpStatus.BAD_REQUEST.value(), "validation error");
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            error.addFieldError(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }

    static class Error {
        private final int status;
        private final String message;
        private final List<FieldError> fieldErrors = new ArrayList<>();

        Error(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public void addFieldError(String objectName, String path, String message) {
            FieldError error = new FieldError(objectName, path, message);
            fieldErrors.add(error);
        }

        public List<FieldError> getFieldErrors() {
            return fieldErrors;
        }
    }
}
