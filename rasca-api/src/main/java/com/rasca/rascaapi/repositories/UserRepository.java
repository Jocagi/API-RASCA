package com.rasca.rascaapi.repositories;

import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.exceptions.EtAuthException;

import java.util.Date;

public interface UserRepository {
    Long create(String Correo, String Contrasena, String Usuario, String Nombres, String Apellidos, String Carnet, String FechaNac, String Telefono, String Fotografia) throws EtAuthException;

    User findByEmailAndPassword(String Correo, String Contrasena) throws EtAuthException;

    Long getCountByEmail(String Correo);

    User findByID(Long IDPersona);

    Long createStudent(Long IDPersona, String IDCarrera, String IDBeca);

    String createApprover(Long IDPersona, String IDCargo);

    Long createAdministrator(Long IDPersona, String IDCargo);
}
