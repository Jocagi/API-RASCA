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
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
