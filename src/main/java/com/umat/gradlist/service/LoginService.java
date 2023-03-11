package com.umat.gradlist.service;

import com.umat.gradlist.dto.LoginDTO;
import com.umat.gradlist.dto.TokenDTO;

public interface LoginService {
    public TokenDTO login(LoginDTO loginDTO);
}
