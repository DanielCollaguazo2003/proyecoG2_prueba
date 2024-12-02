package com.example.IngSoftware.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/example")
    public String exampleEndpoint() {
        return "Hello, Swagger!";
    }
}