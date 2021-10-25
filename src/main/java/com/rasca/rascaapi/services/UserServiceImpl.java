package com.rasca.rascaapi.services;

import com.rasca.rascaapi.domain.Administrator;
import com.rasca.rascaapi.domain.Approver;
import com.rasca.rascaapi.domain.Student;
import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.exceptions.EtAuthException;
import com.rasca.rascaapi.exceptions.EtResourceNotFoundException;
import com.rasca.rascaapi.exceptions.EtRequestException;
import com.rasca.rascaapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
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
        Long IDPersona;
        Long IDEstudiante;
        Long IDCertificador;
        Long IDAdministrador;
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (Correo != null) Correo = Correo.toLowerCase();
        if(!pattern.matcher(Correo).matches())
            throw new EtRequestException("Email inválido");
        Long count = userRepository.getCountByEmail(Correo);
        if(count > 0)
            throw new EtRequestException("Email ya registrado");
        if(Rol.equals("Estudiante")) {
            if (IDCarrera == null || IDCarrera.length() == 0){
                throw new EtRequestException("IDCarrera Inválido");
            }
            if (IDBeca == null || IDBeca.length() == 0){
                throw new EtRequestException("IDCarrera Inválido");
            }
            IDPersona = userRepository.create(Correo,Contrasena,Usuario,Nombres,Apellidos,Carnet,FechaNac,Telefono,Fotografia);
            IDEstudiante = userRepository.createStudent(IDPersona,IDCarrera,IDBeca);
        }
        else {
            if (IDCargo == null || IDCargo.length() == 0){
                throw new EtRequestException("IDCargo Inválido");
            }
            IDPersona = userRepository.create(Correo,Contrasena,Usuario,Nombres,Apellidos,Carnet,FechaNac,Telefono,Fotografia);
            if (Rol.equals("Certificador")){
                IDCertificador = userRepository.createApprover(IDPersona,IDCargo);
            }
            else if(Rol.equals("Administrador")){
                IDAdministrador = userRepository.createAdministrator(IDPersona,IDCargo);
            }
            else{
                throw new EtRequestException("Rol invalido");
            }
        }

        return userRepository.findByID(IDPersona);
    }


    @Override
    public Approver obtenerCertificador(Long IDPerson) throws EtResourceNotFoundException {
        return userRepository.getApprover(IDPerson);
    }

    @Override
    public Administrator obtenerAdministrador(Long IDPerson) throws EtResourceNotFoundException {
        return userRepository.getAdministrator(IDPerson);
    }

    @Override
    public Student obtenerEstudiante(Long IDPerson) throws EtResourceNotFoundException {
        return userRepository.getStudent(IDPerson);
    }
}
