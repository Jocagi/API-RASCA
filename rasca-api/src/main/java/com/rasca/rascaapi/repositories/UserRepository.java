package com.rasca.rascaapi.repositories;

import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.exceptions.EtAuthException;

public interface UserRepository {
    Integer create(String Correo, String Contrasena, String Usuario, String Nombres, String Apellidos, String Carnet, String FechaNac, String Telefono, String Fotografia) throws EtAuthException;

    User findByEmailAndPassword(String Correo, String Contrasena) throws EtAuthException;

    Integer getCountByEmail(String Correo);

    User findByID(Integer IDPersona);
}
