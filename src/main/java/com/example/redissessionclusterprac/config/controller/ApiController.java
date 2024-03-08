package com.example.redissessionclusterprac.config.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping
    public ResponseEntity<String> jwtAuthTest(Principal principal) {

        return new ResponseEntity<String>(principal.getName(), HttpStatus.OK);
    }
}
