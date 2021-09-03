package com.rasca.rascaapi.domain;

import java.util.Date;

public class User {
    private Integer IDPersona;
    private String Correo;
    private String Contrasena;
    private String Usuario;
    private String Nombres;
    private String Apellidos;
    private String Carnet;
    private String FechaNac;
    private String Telefono;
    private String Fotografia;

    public void setIDPersona(Integer IDPersona) {
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

    public Integer getIDPersona() {
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

    /*
    public User(Integer userId, String firstName, String lastName, String email, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/
}
