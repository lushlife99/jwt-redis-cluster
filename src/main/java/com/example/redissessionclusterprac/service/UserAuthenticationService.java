package com.example.redissessionclusterprac.service;

import com.example.redissessionclusterprac.model.User;
import com.example.redissessionclusterprac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserAuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user =  userRepository.findByUserId(userId)
                .orElseThrow(()-> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return createUserDetails(user);
    }

    private UserDetails createUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUserId(),
                user.getPassword(),
                user.getAuthorities()
        );
    }

}
