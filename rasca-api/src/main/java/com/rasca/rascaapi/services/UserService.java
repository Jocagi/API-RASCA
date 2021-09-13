package com.rasca.rascaapi.services;

import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.exceptions.EtAuthException;

public interface UserService {

    User validateUser(String correo, String contraseña) throws EtAuthException;

    User registerUser(String Correo, String Contrasena,String Usuario,String Nombres,String Apellidos, String Carnet, String FechaNac, String Telefono, String Fotografia, String Rol) throws EtAuthException;

}
