package com.rasca.rascaapi.domain;

public class Approver {
    private Long IDCertificador;
    private Long IDCargo;

    public Approver(Long IDCertificador, Long IDCargo) {
        this.IDCertificador = IDCertificador;
        this.IDCargo = IDCargo;
    }

    public Long getIDCertificador() {
        return IDCertificador;
    }

    public void setIDCertificador(Long IDCertificador) {
        this.IDCertificador = IDCertificador;
    }

    public Long getIDCargo() {
        return IDCargo;
    }

    public void setIDCargo(Long IDCargo) {
        this.IDCargo = IDCargo;
    }
}
