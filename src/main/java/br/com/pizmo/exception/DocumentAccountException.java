package br.com.pizmo.exception;

import org.springframework.web.client.RestClientException;

public class DocumentAccountException extends RestClientException {

    public DocumentAccountException(String message) {
        super(message);
    }

}
