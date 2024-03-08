package com.example.redissessionclusterprac.service;

import com.example.redissessionclusterprac.dto.TokenInfo;
import com.example.redissessionclusterprac.jwt.JwtTokenProvider;
import com.example.redissessionclusterprac.model.User;
import com.example.redissessionclusterprac.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class JwtTestService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

    public void join(String userId, String password) {
        User joinUser = User.builder().roles(Collections.singletonList("ROLE_USER"))
                .userId(userId)
                .username(userId)
                .password(passwordEncoder.encode(password)).build();

        userRepository.save(joinUser);
    }

    public TokenInfo login(String userId, String password, HttpServletRequest request, HttpServletResponse response) {
        User findUser = userRepository.findByUserId(userId).get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);
        Authentication authentication = authenticationManagerBuilder.authenticate(authenticationToken);
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication, response);
        tokenInfo.setId(findUser.getId());
        return tokenInfo;
    }
}
