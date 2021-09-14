package com.senla.util.jwt;

import lombok.Data;

@Data
public class JwtRequest {
    private String login;
    private String password;
}
