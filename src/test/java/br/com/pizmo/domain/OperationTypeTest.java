package br.com.pizmo.domain;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OperationTypeTest {

    @Test
    public void isCompraParcelada() {

        OperationType operation = new OperationType();
        operation.setId(2L);

        assertTrue(operation.isCompraParcelada());
    }

    @Test
    public void isCompraAVista() {

        OperationType operation = new OperationType();
        operation.setId(1L);

        assertTrue(operation.isCompraAVista());
    }

    @Test
    public void isSaque() {

        OperationType operation = new OperationType();
        operation.setId(3L);

        assertTrue(operation.isSaque());
    }

    @Test
    public void isDebitoAVista() {

        OperationType operation = new OperationType();
        operation.setId(1L);

        assertTrue(operation.isDebito());
    }

    @Test
    public void isDebitoAPrazo() {

        OperationType operation = new OperationType();
        operation.setId(2L);

        assertTrue(operation.isDebito());
    }

    @Test
    public void isDebitoSaque() {

        OperationType operation = new OperationType();
        operation.setId(3L);

        assertTrue(operation.isDebito());
    }
}