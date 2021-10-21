package com.rasca.rascaapi.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexResource {

    @RequestMapping("/")
    public ResponseEntity<Map<String,String>> home() {
        Map<String,String> map = new HashMap<>();
        map.put("status", String.valueOf(HttpStatus.OK));
        map.put("message", "Hello World!");
        map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
