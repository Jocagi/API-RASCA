package com.rasca.rascaapi.services;

import com.rasca.rascaapi.domain.Approver;
import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.exceptions.EtAuthException;
import com.rasca.rascaapi.exceptions.EtResourceNotFoundException;
import com.rasca.rascaapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public User validateUser(String Correo, String Contrasena) throws EtAuthException {
        if (Correo != null) Correo = Correo.toLowerCase();
        return userRepository.findByEmailAndPassword(Correo,Contrasena);
    }

    @Override
    public User registerUser(String Correo, String Contrasena, String Usuario, String Nombres, String Apellidos, String Carnet, String FechaNac, String Telefono, String Fotografia, String Rol,String IDCarrera, String IDBeca, String IDCargo) throws EtAuthException {
        Pattern pattern =Pattern.compile("^(.+)@(.+)$");
        if (Correo != null) Correo = Correo.toLowerCase();
        if(!pattern.matcher(Correo).matches())
            throw new EtAuthException("Email inválido");
        Long count = userRepository.getCountByEmail(Correo);
        if(count > 0)
            throw new EtAuthException("Email ya registrado");
        Long IDPersona = userRepository.create(Correo,Contrasena,Usuario,Nombres,Apellidos,Carnet,FechaNac,Telefono,Fotografia);
        if(Rol.equals("Estudiante")) {
            Long IDEstudiante = userRepository.createStudent(IDPersona,IDCarrera,IDBeca);
        }
        else if (Rol.equals("Certificador")){
            Long IDCertificador = userRepository.createApprover(IDPersona,IDCargo);
        }
        else if(Rol.equals("Administrador")){
            Long IDAdministrador = userRepository.createAdministrator(IDPersona,IDCargo);
        }
        else{
            throw new EtAuthException("Rol invalido");
        }

        return userRepository.findByID(IDPersona);
    }
}
