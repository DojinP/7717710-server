package com.kikking.server.api.user.service;

import com.kikking.server.api.user.dto.SignupRequestDTO;
import com.kikking.server.api.user.entity.User;
import com.kikking.server.api.user.exception.IdDuplicationException;
import com.kikking.server.api.user.exception.PasswordConfirmMismatchException;
import com.kikking.server.api.user.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(SignupRequestDTO signupRequestDTO) {

        // 아이디 중복 확인
        if(userDAO.checkIdDuplication(signupRequestDTO.getUsername())) {
            throw new IdDuplicationException("해당 username 을 가진 유저가 이미 존재합니다.");
        }

        // 패스워드 확인 일치 확인
        if(!signupRequestDTO.getPassword().equals(signupRequestDTO.getPasswordConfirm())) {
            throw new PasswordConfirmMismatchException("패스워드 확인 값이 불일치합니다.");
        }

        return userDAO.save(signupRequestDTO);
    }
}
