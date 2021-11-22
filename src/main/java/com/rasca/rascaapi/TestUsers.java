package com.rasca.rascaapi;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.rasca.rascaapi.RascaApiApplication;

import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.resources.UserResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RascaApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestUsers{
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @Test
    public void testLogin() throws Exception{
        Map<String,Object> map = Map.of("Correo","eduardo@rasca.com","Contrasena","password");
        HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String,Object>>(map, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/users/login"),
                HttpMethod.POST, entity, String.class);
      //  String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(response.getBody().contains("token"));
    }
    @Test
    public void testRegister() throws Exception{
        Map<String,Object> map = Map.ofEntries(
                Map.entry("Correo","try5@rasca.com"),
                Map.entry("Contrasena", "password"),
                Map.entry("Usuario", "tray5"),
                Map.entry("Nombres", "Kevin"),
                Map.entry("Apellidos", "Holt"),
                Map.entry("Carnet", "1160118"),
                Map.entry("FechaNac", "23/08/1999"),
                Map.entry("Telefono", "55361555"),
                Map.entry("Fotografia", "-"),
                Map.entry("Rol","Certificador"),
                Map.entry("IDCarrera","1"),
                Map.entry("IDBeca","2"),
                Map.entry("IDCargo","1")
        );
        HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String,Object>>(map, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/users/register"),
                HttpMethod.POST, entity, String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(response.getBody().contains("Registrado exitosamente"));
    }
    @Test
    public void testInformation() throws Exception{
        User user = new User(48L,"eduardo@rasca.com","","","","","1160118","","","");
        UserResource userResource = new UserResource();
        Map<String,String> map = userResource.generateJWTToken(user);
        headers.add("Authorization", "Bearer " + map.get("token"));
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/users/information"),
                HttpMethod.GET, entity, String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        String expected = "{\"Correo\":\"eduardo@rasca.com\",\"Usuario\":\"edavila\",\"Nombres\":\"Eduardo\",\"Apellidos\":\"Davila\",\"Carnet\":\"1160118\",\"FechaNacimiento\":\"1999-08-23\",\"Telefono\":\"55361555\",\"Fotografia\":\"-\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testCreateActivity() throws Exception{
        User user = new User(48L,"eduardo@rasca.com","","","","","1160118","","","");
        UserResource userResource = new UserResource();
        Map<String,String> token = userResource.generateJWTToken(user);
        headers.add("Authorization", "Bearer " + token.get("token"));
        Map<String,Object> map = Map.of("Nombre","Unit Testing 2",
                "Cupo",10,
                "Fecha_Inicio","10/10/2021",
                "Descripcion","",
                "Horas_Otorgadas",0,
                "R_Facultad","Pendiente",
                "R_Year","Pendiente",
                "R_Beca","Pendiente");
        HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String,Object>>(map, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/actividades/create"),
                HttpMethod.POST, entity, String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
