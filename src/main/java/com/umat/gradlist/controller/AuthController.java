package com.umat.gradlist.controller;

import com.umat.gradlist.dto.AuthRequestDTO;
import com.umat.gradlist.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String authenticate(@RequestBody AuthRequestDTO authRequestDTO) {
        return authService.authenticate(authRequestDTO);
    }

}
