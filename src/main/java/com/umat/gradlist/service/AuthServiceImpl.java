package com.umat.gradlist.service;

import com.umat.gradlist.dto.AuthRequestDTO;
import com.umat.gradlist.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public String authenticate(AuthRequestDTO authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtUtils.generateToken(authRequest.getUsername());
        }

        throw new UsernameNotFoundException("invalid user");
    }

}
