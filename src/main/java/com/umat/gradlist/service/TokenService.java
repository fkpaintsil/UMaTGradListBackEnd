package com.umat.gradlist.service;

import com.umat.gradlist.dto.TokenDTO;
import com.umat.gradlist.model.User;

public interface TokenService {
    public TokenDTO generateToken(User user);
}
