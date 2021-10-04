package com.rasca.rascaapi.domain;

public class Student {
    private Long IDEstudiante;
    private Long IDPersona;
    private Long IDCarrera;
    private Long IDBeca;

    public Student(Long IDEstudiante,Long IDCarrera, Long IDBeca){
            this.IDEstudiante=IDEstudiante;
            this.IDCarrera=IDCarrera;
            this.IDBeca=IDBeca;
    }
    public Long getIDEstudiante() {
        return IDEstudiante;
    }

    public void setIDEstudiante(Long IDEstudiante) {
        this.IDEstudiante = IDEstudiante;
    }

    public Long getIDPersona() {
        return IDPersona;
    }

    public void setIDPersona(Long IDPersona) {
        this.IDPersona = IDPersona;
    }

    public Long getIDCarrera() {
        return IDCarrera;
    }

    public void setIDCarrera(Long IDCarrera) {
        this.IDCarrera = IDCarrera;
    }

    public Long getIDBeca() {
        return IDBeca;
    }

    public void setIDBeca(Long IDBeca) {
        this.IDBeca = IDBeca;
    }
}
