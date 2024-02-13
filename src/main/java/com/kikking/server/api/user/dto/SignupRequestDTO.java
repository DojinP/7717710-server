package com.kikking.server.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class SignupRequestDTO {
    String username;
    String password;
    String passwordConfirm;
}
