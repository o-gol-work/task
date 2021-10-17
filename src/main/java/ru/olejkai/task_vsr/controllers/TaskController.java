package ru.olejkai.task_vsr.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.olejkai.task_vsr.entity.DepartmentEntity;
import ru.olejkai.task_vsr.entity.TaskEntity;
import ru.olejkai.task_vsr.repository.DepartmentRepository;
import ru.olejkai.task_vsr.repository.TaskRepository;
import ru.olejkai.task_vsr.search.TaskSearchValues;

import java.sql.Timestamp;
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

    @PostMapping("/search")
    public Collection<TaskEntity> findTaskEntitiesByParam(@RequestBody TaskSearchValues taskSearchValues){
        return taskRepository.findTaskEntitiesByParam(
                taskSearchValues.getEmployeeIdTasker()
                ,taskSearchValues.getTaskProblemId()
                ,taskSearchValues.getDateBegin()
                /*,taskSearchValues.getEmployeeIdExecuter(),
                taskSearchValues.getDepartmentIdExecuter(),
                taskSearchValues.getDataFinish(),
                taskSearchValues.getStatus()*/
        );

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
