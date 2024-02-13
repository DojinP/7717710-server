package com.kikking.server.api.auth.controller;

import com.kikking.server.api.auth.dto.LoginRequestDTO;
import com.kikking.server.api.auth.service.AuthService;
import com.kikking.server.api.format.BasicResponseMsg;
import com.kikking.server.api.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<BasicResponseMsg> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        BasicResponseMsg basicResponseMsg;
        // 유저 정보 확인
        User user = authService.login(loginRequestDTO);

        // 로그인 성공
        if(user != null) {
            basicResponseMsg = BasicResponseMsg.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("로그인 성공")
                    .result(List.of(user))
                    .count(1).build();
        }else { // 로그인 실패
            basicResponseMsg = BasicResponseMsg.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("로그인 실패")
                    .result(Collections.emptyList())
                    .count(0).build();
        }

        return new ResponseEntity<>(basicResponseMsg, basicResponseMsg.getHttpStatus());
    }

}
