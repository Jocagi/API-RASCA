package com.rasca.rascaapi.resources;

import com.rasca.rascaapi.domain.Activities;
import com.rasca.rascaapi.domain.Approver;
import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.repositories.UserRepository;
import com.rasca.rascaapi.services.ActivityService;
import com.rasca.rascaapi.services.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/actividades")
public class ActivityResource {

    @Autowired
    ActivityService activityService;

    @GetMapping("/getPending")
    public ResponseEntity<Object> getPending(HttpServletRequest request){
        List<Activities> entityList = activityService.obtenerActividades("P");
        List<JSONObject> entities = new ArrayList<JSONObject>();
        for (Activities n : entityList) {
            JSONObject entity = new JSONObject();
            entity.put("ID_Actividad", n.getID_Actividad());
            entity.put("Nombre", n.getNombre());
            entity.put("Cupo", n.getCupo());
            entity.put("Fecha_Inicio", n.getFecha_Inicio());
            entity.put("Estado", n.getEstado());
            entity.put("Descripcion", n.getDescripcion());
            entity.put("Horas_Otorgadas", n.getHoras_Otorgadas());
            entity.put("R_Facultad", n.getR_Facultad());
            entity.put("R_Year", n.getR_Year());
            entity.put("R_Beca", n.getR_Beca());
            entity.put("ID_Certificador", n.getID_Certificador());
            entity.put("ID_Administrador", n.getID_Administrador());
            entities.add(entity);
        }
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }

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

    @PostMapping("/approve")
    public ResponseEntity<Map<String,String>> approve(HttpServletRequest request, @RequestBody Map<String,Object> categoryMap) {
        //TODO: Obtener ID de administrador del header
        int IDPersona = (int) request.getAttribute("IDPersona");
        long ID_Administrador = 1; //TODO
        long ID_Actividad = (int) categoryMap.get("IDActividad");
        activityService.aprobarActividad(ID_Actividad, ID_Administrador);
        Map<String,String> map = new HashMap<>();
        map.put("status", String.valueOf(HttpStatus.OK));
        map.put("message", "Actividad aprobada");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/reject")
    public ResponseEntity<Map<String,String>> reject(HttpServletRequest request, @RequestBody Map<String,Object> categoryMap) {
        //TODO: Obtener ID de administrador del header
        int IDPersona = (int) request.getAttribute("IDPersona");
        long ID_Administrador = 1; //TODO
        long ID_Actividad = (int) categoryMap.get("IDActividad");
        activityService.rechazarActividad(ID_Actividad, ID_Administrador);
        Map<String,String> map = new HashMap<>();
        map.put("status", String.valueOf(HttpStatus.OK));
        map.put("message", "Actividad rechazada");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<Map<String,String>> cancel(HttpServletRequest request, @RequestBody Map<String,Object> categoryMap) {
        //TODO: Obtener ID de administrador del header
        int IDPersona = (int) request.getAttribute("IDPersona");
        long ID_Administrador = 1; //TODO
        long ID_Actividad = (int) categoryMap.get("IDActividad");
        activityService.rechazarActividad(ID_Actividad, ID_Administrador);
        Map<String,String> map = new HashMap<>();
        map.put("status", String.valueOf(HttpStatus.OK));
        map.put("message", "Actividad rechazada");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
