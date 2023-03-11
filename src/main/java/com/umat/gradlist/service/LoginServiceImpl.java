package com.umat.gradlist.service;

import com.umat.gradlist.dto.LoginDTO;
import com.umat.gradlist.dto.TokenDTO;
import com.umat.gradlist.model.User;
import com.umat.gradlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository, final TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }
    @Override
    public TokenDTO login(LoginDTO loginDTO) {
        final String INVALID_DETAILS_MESSAGE = "Invalid username or password";

        User user = this.userRepository.findByEmail(loginDTO.getEmail());

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, INVALID_DETAILS_MESSAGE);
        }

        if (!(new BCryptPasswordEncoder().matches(loginDTO.getPassword(), user.getPassword()))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, INVALID_DETAILS_MESSAGE);
        }

        return tokenService.generateToken(user);
    }
}
