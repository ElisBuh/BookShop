package com.senla.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class UserDto {
    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}
