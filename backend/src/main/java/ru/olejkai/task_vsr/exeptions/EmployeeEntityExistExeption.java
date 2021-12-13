package ru.olejkai.task_vsr.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeEntityExistExeption extends RuntimeException {
    public EmployeeEntityExistExeption(String name) {
        System.out.printf("{} exists",name);
    }
}
