package br.com.pizmo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "operationtype")
public class OperationType {

    public static final long COMPRA_AVISTA = 1L;
    public static final long COMPRA_PARCELADA = 2L;
    public static final long SAQUE = 3L;

    @Id
    private Long id;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompraParcelada() {

        return getId().equals(COMPRA_PARCELADA);
    }

    public boolean isCompraAVista() {

        return getId().equals(COMPRA_AVISTA);
    }

    public boolean isSaque() {

        return getId().equals(SAQUE);
    }

    public boolean isDebito() {

        return isCompraAVista() || isCompraParcelada() || isSaque();
    }
}
