package com.rasca.rascaapi.domain;

import java.util.Date;

public class Activities {
    private Long ID_Actividad;
    private String Nombre;
    private Integer Cupo;
    private Date Fecha_Inicio;
    private char Estado;
    private String Descripcion;
    private Integer Horas_Otorgadas;
    private String R_Facultad;
    private String R_Year;
    private String R_Beca;
    private String ID_Certificador;
    private String ID_Administrador;

    public Long getID_Actividad() {
        return ID_Actividad;
    }

    public void setID_Actividad(Long ID_Actividad) {
        this.ID_Actividad = ID_Actividad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Integer getCupo() {
        return Cupo;
    }

    public void setCupo(Integer cupo) {
        Cupo = cupo;
    }

    public Date getFecha_Inicio() {
        return Fecha_Inicio;
    }

    public void setFecha_Inicio(Date fecha_Inicio) {
        Fecha_Inicio = fecha_Inicio;
    }

    public char getEstado() {
        return Estado;
    }

    public void setEstado(char estado) {
        Estado = estado;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Integer getHoras_Otorgadas() {
        return Horas_Otorgadas;
    }

    public void setHoras_Otorgadas(Integer horas_Otorgadas) {
        Horas_Otorgadas = horas_Otorgadas;
    }

    public String getR_Facultad() {
        return R_Facultad;
    }

    public void setR_Facultad(String r_Facultad) {
        R_Facultad = r_Facultad;
    }

    public String getR_Year() {
        return R_Year;
    }

    public void setR_Year(String r_Year) {
        R_Year = r_Year;
    }

    public String getR_Beca() {
        return R_Beca;
    }

    public void setR_Beca(String r_Beca) {
        R_Beca = r_Beca;
    }

    public String getID_Certificador() {
        return ID_Certificador;
    }

    public void setID_Certificador(String ID_Certificador) {
        this.ID_Certificador = ID_Certificador;
    }

    public String getID_Administrador() {
        return ID_Administrador;
    }

    public void setID_Administrador(String ID_Administrador) {
        this.ID_Administrador = ID_Administrador;
    }
}
