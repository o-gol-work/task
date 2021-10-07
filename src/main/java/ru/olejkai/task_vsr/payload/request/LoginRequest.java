package ru.olejkai.task_vsr.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {
    @NotEmpty(message = "Username not empty")
    private String username;

    @NotEmpty(message = "Password not empty")
    private String password;
}
