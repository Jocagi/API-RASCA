package com.rasca.rascaapi.resources;

import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Map<String,Object> userMap) {
        String Correo = (String) userMap.get("Correo");
        String Contraseña = (String) userMap.get("Contraseña");
        User user = userService.validateUser(Correo,Contraseña);
        Map<String,String> map = new HashMap<>();
        map.put("message", "Log in exitoso");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


        @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@RequestBody Map<String,Object> userMap){
        String Correo = (String) userMap.get("Correo");
        String Contrasena = (String) userMap.get("Contrasena");
        String Usuario = (String) userMap.get("Usuario");
        String Nombres = (String) userMap.get("Nombres");
        String Apellidos = (String) userMap.get("Apellidos");
        String Carnet = (String) userMap.get("Carnet");
        String FechaNac = (String) userMap.get("FechaNac");
        String Telefono = (String) userMap.get("Telefono");
        String Fotografia = (String) userMap.get("Fotografia");
        String Rol = (String)userMap.get("Rol");
        User user = userService.registerUser(Correo, Contrasena, Usuario, Nombres, Apellidos, Carnet, FechaNac, Telefono, Fotografia, Rol);
        Map<String,String> map = new HashMap<>();
        map.put("message", "Registrado exitosamente");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
