package com.rasca.rascaapi.resources;

import com.rasca.rascaapi.Constants;
import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
        String Contrasena = (String) userMap.get("Contrasena");
        User user = userService.validateUser(Correo,Contrasena);      
        return new ResponseEntity<>(generateJWTToken(user),HttpStatus.OK);
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
        String IDCarrera = (String)userMap.get("IDCarrera");
        String IDBeca = (String)userMap.get("IDBeca");
        String IDCargo = (String)userMap.get("IDCargo");
        User user = userService.registerUser(Correo, Contrasena, Usuario, Nombres, Apellidos, Carnet, FechaNac, Telefono, Fotografia, Rol, IDCarrera,IDBeca,IDCargo);
        Map<String,String> map = new HashMap<>();
        map.put("message", "Registrado exitosamente");
  return new ResponseEntity<>(map, HttpStatus.OK);
    }

    private Map<String,String> generateJWTToken (User user){
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY())
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("IDPersona", user.getIDPersona())
                .claim("Correo", user.getCorreo())
                .claim("Carnet", user.getCarnet())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token",token);
        return map;
    }
}
