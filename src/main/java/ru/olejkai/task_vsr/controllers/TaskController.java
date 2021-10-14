package ru.olejkai.task_vsr.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.olejkai.task_vsr.entity.DepartmentEntity;
import ru.olejkai.task_vsr.entity.TaskEntity;
import ru.olejkai.task_vsr.repository.DepartmentRepository;
import ru.olejkai.task_vsr.repository.TaskRepository;

import java.util.Collection;

@RestController
@RequestMapping("start/tasks")
public class TaskController {

    private TaskRepository taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    private static final Logger LOG = LoggerFactory.getLogger(DepartmentController.class);


    @GetMapping("/all_tasks")
    public Collection<TaskEntity> taskEntities(){
        return taskRepository.findAll();
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskEntity> getTask(@PathVariable Long id){

//        return taskRepository.getTaskEntityById(275l);
        TaskEntity taskEntity=null;
        try {
            taskEntity=taskRepository.findById(id).get();
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("task with id={} not found",id);
            return new ResponseEntity(String.format("task with id=%s not found",id), HttpStatus.NOT_ACCEPTABLE);


        }

        return ResponseEntity.ok(taskEntity);
    }

    @GetMapping("/parent_task/{id}")
    public ResponseEntity<TaskEntity> getParent(@PathVariable Long id){
        TaskEntity taskEntity=null;
        try {
            taskEntity=taskRepository.findById(id).get().getParent();
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("task with id={} not found",id);
            return new ResponseEntity(String.format("task with id=%s not found",id), HttpStatus.NOT_ACCEPTABLE);


        }

        return ResponseEntity.ok(taskEntity);
    }

    @GetMapping("/children_first_line_tasks/{id}")
    public ResponseEntity<Collection<TaskEntity>> getChildren(@PathVariable Long id){
        Collection<TaskEntity> taskEntity=null;
        try {
            taskEntity=taskRepository.findById(id).get().getChildren();
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("task with id={} not found",id);
            return new ResponseEntity(String.format("task with id=%s not found",id), HttpStatus.NOT_ACCEPTABLE);


        }

        return ResponseEntity.ok(taskEntity);
    }

    @GetMapping("/all_children__tasks/{id}")
    public ResponseEntity<Collection<TaskEntity>> getAllChildren(@PathVariable Long id){
        Collection<TaskEntity> taskEntity=null;
        try {
            taskEntity=taskRepository.getAllChildren(id);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("task with id={} not found",id);
            return new ResponseEntity(String.format("task with id=%s not found",id), HttpStatus.NOT_ACCEPTABLE);


        }

        return ResponseEntity.ok(taskEntity);
    }

    @GetMapping("/all_parent_tasks/{id}")
    public ResponseEntity<Collection<TaskEntity>> getAllParent(@PathVariable Long id) {
        Collection<TaskEntity> taskEntity=null;
        try {
            taskEntity=taskRepository.getAllParent(id);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("task with id={} not found",id);
            return new ResponseEntity(String.format("task with id=%s not found",id), HttpStatus.NOT_ACCEPTABLE);


        }

        return ResponseEntity.ok(taskEntity);
    }


}
