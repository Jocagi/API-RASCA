package com.rasca.rascaapi.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/actividades")
public class ActivityResource {
    @GetMapping("")
    public String getAllActivities(HttpServletRequest request){
        int userId = (Integer) request.getAttribute("userId");
        return "Usuario Autenticado! UserId: " + userId;
    }
}
