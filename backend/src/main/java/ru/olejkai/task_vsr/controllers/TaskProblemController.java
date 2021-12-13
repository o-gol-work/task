package ru.olejkai.task_vsr.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.entity.TaskEntity;
import ru.olejkai.task_vsr.entity.TaskProblemEntity;
import ru.olejkai.task_vsr.search.TaskSearchValues;
import ru.olejkai.task_vsr.services.dbAccessServices.EmployeeServices;
import ru.olejkai.task_vsr.services.dbAccessServices.TaskProblemServices;
import ru.olejkai.task_vsr.services.dbAccessServices.TaskServices;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/tasks_problem")
//@RequestMapping("start/tasks_problem")
public class TaskProblemController {

    private TaskProblemServices taskProblemServices;


    @Autowired
    public TaskProblemController(TaskProblemServices taskProblemServices) {
        this.taskProblemServices = taskProblemServices;

    }



    private static final Logger LOG = LoggerFactory.getLogger(TaskProblemServices.class);



    @GetMapping("/by_empl_tasker")
    public ResponseEntity<Collection<TaskProblemEntity>> getTasksByEmployeeTaskerId(@NotNull Principal principal) {
        return ResponseEntity.ok(taskProblemServices.findTaskProblemByEmployeeTaskerId(principal));
    }

    /*@GetMapping("/by_empl_tasker/{employeeTaskerId}")
    public ResponseEntity<Collection<TaskProblemEntity>> getTasksByEmployeeTaskerId(@PathVariable @NotNull Long employeeTaskerId) {
        return ResponseEntity.ok(taskProblemServices.findTaskProblemByEmployeeTaskerId(employeeTaskerId));
    }*/
}
