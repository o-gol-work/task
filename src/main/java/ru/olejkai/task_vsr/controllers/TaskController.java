package ru.olejkai.task_vsr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.olejkai.task_vsr.entity.DepartmentEntity;
import ru.olejkai.task_vsr.entity.TaskEntity;
import ru.olejkai.task_vsr.repository.DepartmentRepository;
import ru.olejkai.task_vsr.repository.TaskRepository;

import java.util.Collection;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskRepository taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @GetMapping("/task")
    public TaskEntity getTask(){
        return taskRepository.getTaskEntityById(275l);
    }

    @GetMapping("/parent_task")
    public TaskEntity getParent(){
        return taskRepository.getTaskEntityById(280l).getParent();
    }

    @GetMapping("/children_first_line_tasks")
    public Collection<TaskEntity> getChildren(){
        return taskRepository.getTaskEntityById(275l).getChildren();
    }

    @GetMapping("/all_children__tasks")
    public Collection<TaskEntity> getAllChildren(){
        return taskRepository.getAllChildren(275l);
    }

    @GetMapping("/all_parent_tasks")
    public Collection<TaskEntity> getAllParent() {
        return taskRepository.getAllParent(280l);
    }


}
