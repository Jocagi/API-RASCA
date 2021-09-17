package com.rasca.rascaapi.resources;

import com.rasca.rascaapi.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/actividades")
public class ActivityResource {
    @GetMapping("")
    public String getAllActivities(HttpServletRequest request){
        int userId = (Integer) request.getAttribute("IDPersona");
        return "Usuario Autenticado! UserId: " + userId;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,String>> create(@RequestBody Map<String,Object> userMap) {
        String Correo = (String) userMap.get("Correo");
        String Contrasena = (String) userMap.get("Contrasena");
     //   User user = userService.validateUser(Correo,Contrasena);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
