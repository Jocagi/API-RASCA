package com.rasca.rascaapi.services;

import com.rasca.rascaapi.domain.Administrator;
import com.rasca.rascaapi.domain.Approver;
import com.rasca.rascaapi.domain.Student;
import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.exceptions.EtAuthException;
import com.rasca.rascaapi.exceptions.EtResourceNotFoundException;

public interface UserService {

    User validateUser(String correo, String contrasena) throws EtAuthException;

    User registerUser(String Correo, String Contrasena,String Usuario,String Nombres,String Apellidos, String Carnet, String FechaNac, String Telefono, String Fotografia, String Rol, String IDCarrera, String IDBeca, String IDCargo) throws EtAuthException;

    Approver obtenerCertificador (Long IDPerson)throws EtResourceNotFoundException;
    Administrator obtenerAdministrador (Long IDPerson)throws EtResourceNotFoundException;
    Student obtenerEstudiante (Long IDPerson)throws EtResourceNotFoundException;
}
