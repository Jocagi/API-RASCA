package com.rasca.rascaapi.domain;

public class Administrator {
    private Long IDAdministrador;
    private Long IDPersona;
    private Long IDCargo;

    public Administrator(Long IDAdministrador, Long IDCargo){
        this.IDAdministrador=IDAdministrador;
        this.IDCargo=IDCargo;
    }

    public Long getIDAdministrador() {
        return IDAdministrador;
    }

    public void setIDAdministrador(Long IDAdministrador) {
        this.IDAdministrador = IDAdministrador;
    }

    public Long getIDPersona() {
        return IDPersona;
    }

    public void setIDPersona(Long IDPersona) {
        this.IDPersona = IDPersona;
    }

    public Long getIDCargo() {
        return IDCargo;
    }

    public void setIDCargo(Long IDCargo) {
        this.IDCargo = IDCargo;
    }
}
