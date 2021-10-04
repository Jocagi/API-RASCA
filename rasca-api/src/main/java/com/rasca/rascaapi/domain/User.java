package com.rasca.rascaapi.domain;

import java.util.Date;

public class User {
    private Long IDPersona;
    private String Correo;
    private String Contrasena;
    private String Usuario;
    private String Nombres;
    private String Apellidos;
    private String Carnet;
    private String FechaNac;
    private String Telefono;
    private String Fotografia;

    public void setIDPersona(Long IDPersona) {
        this.IDPersona = IDPersona;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public void setCarnet(String carnet) {
        Carnet = carnet;
    }

    public void setFechaNac(String fechaNac) {
        FechaNac = fechaNac;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public void setFotografia(String fotografia) {
        Fotografia = fotografia;
    }

    public Long getIDPersona() {
        return IDPersona;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public String getUsuario() {
        return Usuario;
    }

    public String getNombres() {
        return Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getCarnet() {
        return Carnet;
    }

    public String getFechaNac() {
        return FechaNac;
    }

    public String getTelefono() {
        return Telefono;
    }

    public String getFotografia() {
        return Fotografia;
    }

    public User(Long IDPersona, String Correo, String Contrasena, String Usuario, String Nombres, String Apellidos, String Carnet, String FechaNac, String Telefono, String Fotografia) {
        this.IDPersona = IDPersona;
        this.Correo = Correo;
        this.Contrasena = Contrasena;
        this.Usuario = Usuario;
        this.Nombres = Nombres;
        this.Apellidos=Apellidos;
        this.Carnet=Carnet;
        this.FechaNac=FechaNac;
        this.Telefono=Telefono;
        this.Fotografia=Fotografia;
    }

}
