package com.rasca.rascaapi.resources;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.rasca.rascaapi.RascaApiApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
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
                Map.entry("Correo","try2@rasca.com"),
                Map.entry("Contrasena", "password"),
                Map.entry("Usuario", "tray2"),
                Map.entry("Nombres", "Raymond"),
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
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
