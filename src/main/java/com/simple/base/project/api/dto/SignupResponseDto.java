package com.simple.base.project.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class SignupResponseDto {
    private String user;
    private String token;
}
