package com.example.redissessionclusterprac.config.controller;

import com.example.redissessionclusterprac.dto.TokenInfo;
import com.example.redissessionclusterprac.service.JwtTestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisJwtAuthController {

    private final JwtTestService jwtTestService;

    @PostMapping("/join")
    public ResponseEntity join(@RequestParam("userId") String userId, @RequestParam("password") String password) {
        jwtTestService.join(userId, password);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public TokenInfo login(@RequestParam("userId") String userId, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
        return jwtTestService.login(userId, password, request, response);
    }
}
