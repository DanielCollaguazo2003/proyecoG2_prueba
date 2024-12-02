package com.example.IngSoftware.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SwaggerController {

    @GetMapping("/swagger")
    public String swagger() {
        return "Swagger documentation is available at /swagger-ui.html";
    }
}