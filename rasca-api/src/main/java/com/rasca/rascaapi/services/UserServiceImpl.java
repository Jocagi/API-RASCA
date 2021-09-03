package com.rasca.rascaapi.services;

import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.exceptions.EtAuthException;
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
    public User validateUser(String Correo, String Contraseña) throws EtAuthException {
        if (Correo != null) Correo = Correo.toLowerCase();
        return userRepository.findByEmailAndPassword(Correo,Contraseña);
    }

    @Override
    public User registerUser(String Correo, String Contrasena, String Usuario, String Nombres, String Apellidos, String Carnet, String FechaNac, String Telefono, String Fotografia) throws EtAuthException {
        Pattern pattern =Pattern.compile("^(.+)@(.+)$");
        if (Correo != null) Correo = Correo.toLowerCase();
        if(!pattern.matcher(Correo).matches())
            throw new EtAuthException("Email inválido");
        Integer count = userRepository.getCountByEmail(Correo);
        if(count > 0)
            throw new EtAuthException("Email ya registrado");
        Integer IDPersona = userRepository.create(Correo,Contrasena,Usuario,Nombres,Apellidos,Carnet,FechaNac,Telefono,Fotografia);
        return userRepository.findByID(IDPersona);
    }
/*
    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        Pattern pattern =Pattern.compile("^(.+)@(.+)$");
        if (email != null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new EtAuthException("Email inválido");
        Integer count = userRepository.getCountByEmail(email);
        if(count > 0)
            throw new EtAuthException("Email ya registrado");
        Integer userId = userRepository.create(firstName, lastName, email, password);
        return userRepository.findByID(userId);
    }*/
}
