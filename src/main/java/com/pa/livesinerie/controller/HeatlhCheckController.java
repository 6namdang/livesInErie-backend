package com.pa.livesinerie.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/health")
public class HeatlhCheckController {
    
    @GetMapping
    public ResponseEntity<String> HealthCheck() {
        return ResponseEntity.ok("Ok");
    }
}
