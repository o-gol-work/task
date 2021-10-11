package ru.olejkai.task_vsr.payload.request;

import lombok.Data;
import ru.olejkai.task_vsr.annotation.PasswordMatchers;

import javax.validation.constraints.NotEmpty;

@PasswordMatchers(message = "wrong confirmPassword")
@Data
public class SignupRequest {





    @NotEmpty(message = "tabelNumber not empty")
    private String tabelNumber;
    /*@NotEmpty(message = "username not empty")
    private  String username;*/
    @NotEmpty(message = "name not empty")
    private  String name;
    @NotEmpty(message = "surname not empty")
    private String  surname;
    private String telephoneNumber;
    @NotEmpty(message = "postHasDepartmentId not empty")
    private String postHasDepartmentId;
    @NotEmpty(message = "password not empty")
    private  String password;
    private  String confirmPassword;

}
