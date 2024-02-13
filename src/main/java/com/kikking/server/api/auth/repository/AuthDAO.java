package com.kikking.server.api.auth.repository;

import com.kikking.server.api.auth.exception.UserNotFoundException;
import com.kikking.server.api.user.entity.User;
import com.kikking.server.api.user.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AuthDAO {

    private final UserRepository userRepository;

    public AuthDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
