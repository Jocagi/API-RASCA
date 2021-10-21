package com.rasca.rascaapi.resources;

import com.rasca.rascaapi.domain.Activities;
import com.rasca.rascaapi.domain.Approver;
import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.repositories.UserRepository;
import com.rasca.rascaapi.services.ActivityService;
import com.rasca.rascaapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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



    @Autowired
    ActivityService activityService;
    @PostMapping("/create")
    public ResponseEntity<Map<String,String>> create(HttpServletRequest request, @RequestBody Map<String,Object> categoryMap) {
        int IDPersona = (int) request.getAttribute("IDPersona");
        Approver certificador = activityService.obtenerCertificador(Long.valueOf(IDPersona));
        String Nombre = (String) categoryMap.get("Nombre");
        int Cupo= (int) categoryMap.get("Cupo");
        String Fecha_Inicio = (String) categoryMap.get("Fecha_Inicio");
        String Descripcion = (String) categoryMap.get("Descripcion");
        int Horas_Otorgadas= (int) categoryMap.get("Horas_Otorgadas");
        String R_Facultad = (String) categoryMap.get("R_Facultad");
        String R_Year = (String) categoryMap.get("R_Year");
        String R_Beca = (String) categoryMap.get("R_Beca");
        Activities activity = activityService.agregaActividad(Nombre,Cupo,Fecha_Inicio,Descripcion,Horas_Otorgadas,R_Facultad,R_Year,R_Beca,certificador.getIDCertificador(), 4L);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
