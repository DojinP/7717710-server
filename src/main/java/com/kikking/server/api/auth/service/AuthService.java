package com.kikking.server.api.auth.service;

import com.kikking.server.api.auth.exception.PasswordMismatchedException;
import com.kikking.server.api.auth.dto.LoginRequestDTO;
import com.kikking.server.api.auth.exception.UserNotFoundException;
import com.kikking.server.api.auth.repository.AuthDAO;
import com.kikking.server.api.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthDAO authDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthDAO authDAO, PasswordEncoder passwordEncoder) {
        this.authDAO = authDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(LoginRequestDTO loginRequestDTO) {

        // get 유저
        User user = authDAO.getUser(loginRequestDTO.getUsername());

        // 유저가 없는 경우 예외처리
        if(user == null) {
            throw new UserNotFoundException("username 과 일치하는 계정이 없습니다");
        }

        // 입력한 패스워드와 유저 패스워드가 일치하지 않는 경우
        if(!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new PasswordMismatchedException("패스워드 정보가 일치하지 않습니다.");
        }

        return user;
    }

}
