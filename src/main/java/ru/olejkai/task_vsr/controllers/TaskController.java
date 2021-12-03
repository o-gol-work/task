package ru.olejkai.task_vsr.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.olejkai.task_vsr.services.dbAccessServices.TaskServices;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskServices taskServices;


    @Autowired
    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }



    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);






}
