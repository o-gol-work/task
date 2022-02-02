package ru.olejkai.task_vsr.payload.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.olejkai.task_vsr.entity.EmployeeEntity;

@Data
@AllArgsConstructor
public class JWTTokenSuccessResponse {
    private boolean success;
    private String token;
    private EmployeeEntity employee;
}
