package com.rasca.rascaapi.resources;

import com.rasca.rascaapi.Constants;
import com.rasca.rascaapi.domain.Activities;
import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.domain.Administrator;
import com.rasca.rascaapi.domain.Approver;
import com.rasca.rascaapi.domain.Student;}
import com.rasca.rascaapi.services.ActivityService;
import com.rasca.rascaapi.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    UserService userService;
    @Autowired
    ActivityService activityService;

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
        map.put("status", String.valueOf(HttpStatus.OK));
        map.put("message", "Registrado exitosamente");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/information")
    public ResponseEntity<Object> getInfo(HttpServletRequest request){
        int IDPersona = (int) request.getAttribute("IDPersona");
        User informacionUsuario = userService.obtenerInformacion(Long.valueOf(IDPersona));
        JSONObject entity = new JSONObject();
        entity.put("Correo", informacionUsuario.getCorreo());
        entity.put("Usuario", informacionUsuario.getUsuario());
        entity.put("Nombres", informacionUsuario.getNombres());
        entity.put("Apellidos", informacionUsuario.getApellidos());
        entity.put("Carnet", informacionUsuario.getCarnet());
        entity.put("FechaNacimiento", informacionUsuario.getFechaNac());
        entity.put("Telefono", informacionUsuario.getTelefono());
        entity.put("Fotografia", informacionUsuario.getFotografia());
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    private Map<String,String> generateJWTToken (User user){
        long timestamp = System.currentTimeMillis();
        String rol = getUserRole(user);
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY())
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("IDPersona", user.getIDPersona())
                .claim("Correo", user.getCorreo())
                .claim("Carnet", user.getCarnet())
                .claim("Rol", rol)
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token",token);
        map.put("Rol", rol);
        return map;
    }

    private String getUserRole(User user) {
        long id = user.getIDPersona();
        String rol = "Undefined";
        Approver certificador = null;
        Student estudiante = null;
        Administrator admin = null;
        try{
            certificador = activityService.obtenerCertificador(id);
        }catch (Exception e){}
        try{
            estudiante = activityService.obtenerEstudiante(id);
        }catch (Exception e){}
        try{
            admin = activityService.obtenerAdministrador(id);
        }catch (Exception e){}

        if (admin != null) {
            rol = "Administrador";
        }
        else if (estudiante != null) {
            rol = "Estudiante";
        }
        else if (certificador != null) {
            rol = "Certificador";
        }
        return rol;
    }
}
