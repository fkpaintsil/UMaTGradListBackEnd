package com.umat.gradlist.service;

import com.umat.gradlist.dto.AuthRequestDTO;

public interface AuthService {
    String authenticate(AuthRequestDTO authRequest);
}
