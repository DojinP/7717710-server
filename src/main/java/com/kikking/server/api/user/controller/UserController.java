package com.kikking.server.api.user.controller;

import com.kikking.server.api.format.BasicResponseMsg;
import com.kikking.server.api.user.dto.SignupRequestDTO;
import com.kikking.server.api.user.entity.User;
import com.kikking.server.api.user.service.UserService;
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
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<BasicResponseMsg> signup(@RequestBody SignupRequestDTO signupRequestDTO) {

        BasicResponseMsg basicResponseMsg;
        User user = userService.signup(signupRequestDTO);

        if(user != null) {
            basicResponseMsg = BasicResponseMsg.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("User 생성 성공")
                    .result(List.of(user))
                    .count(1).build();
        }else {
            basicResponseMsg = BasicResponseMsg.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("User 생성 실패")
                    .result(Collections.emptyList())
                    .count(0).build();
        }

        System.out.println(basicResponseMsg);

        return new ResponseEntity<>(basicResponseMsg, basicResponseMsg.getHttpStatus());
    }
}
