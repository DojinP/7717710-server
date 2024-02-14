package com.kikking.server.api.user.repository;

import com.kikking.server.api.user.dto.SignupRequestDTO;
import com.kikking.server.api.user.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDAO(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean checkIdDuplication(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public User save(SignupRequestDTO signupRequestDTO) {
        User toBeSavedUser = User.builder()
                .username(signupRequestDTO.getUsername())
                .password(passwordEncoder.encode(signupRequestDTO.getPassword()))
                .build();

        return userRepository.saveUser(toBeSavedUser);
    }
}
