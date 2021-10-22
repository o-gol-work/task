package ru.olejkai.task_vsr.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.olejkai.task_vsr.entity.DepartmentEntity;
import ru.olejkai.task_vsr.entity.TaskEntity;
import ru.olejkai.task_vsr.repository.DepartmentRepository;
import ru.olejkai.task_vsr.repository.TaskRepository;
import ru.olejkai.task_vsr.search.TaskSearchValues;
import ru.olejkai.task_vsr.services.dbAccessServices.TaskServices;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.Collection;

@RestController
@RequestMapping("start/tasks")
public class TaskController {

    private TaskServices taskServices;

    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }



    private static final Logger LOG = LoggerFactory.getLogger(DepartmentController.class);


    @GetMapping("/all_tasks")
    public Collection<TaskEntity> taskEntities(){
        return taskServices.findAll();
    }



    @PostMapping("/search")
    public ResponseEntity<Page<TaskEntity>> findTaskEntitiesByParam(@RequestBody TaskSearchValues taskSearchValues){

        String sortColumn=taskSearchValues.getSortColumn()!=null?taskSearchValues.getSortColumn():null;
        String sortDirection=taskSearchValues.getSortDirection()!=null?taskSearchValues.getSortDirection():null;

        Integer pageNumber=taskSearchValues.getPageNumber()!=null?taskSearchValues.getPageNumber():null;
        Integer pageSize=taskSearchValues.getPageSize()!=null?taskSearchValues.getPageSize():null;

        Sort.Direction direction =sortDirection==null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc")? Sort.Direction.ASC:Sort.Direction.DESC;

        Sort sort=Sort.by(direction,sortColumn);



        PageRequest pageRequest = PageRequest.of(
                pageNumber
                ,pageSize
                ,sort
                );

        Page<TaskEntity> result=taskServices.findTaskEntitiesByParamOne(
                taskSearchValues
                ,pageRequest
        );
        /*Page result=taskRepository.findTaskEntitiesByParamOne(
                taskSearchValues.getEmployeeIdTasker()
                ,taskSearchValues.getTaskProblemId()
                ,taskSearchValues.getDateBegin()
                ,
                taskSearchValues.getEmployeeIdExecuter()
                ,taskSearchValues.getDepartmentIdExecuter()
                , taskSearchValues.getDataFinish()
                , taskSearchValues.getStatus()
                ,pageRequest
        );*/


//        if(taskSearchValues.getEmployeeIdExecuter()!=null && taskSearchValues.getEmployeeIdExecuter()!=""){
        return ResponseEntity.ok(result);
        /*}else {
            return taskRepository.findTaskEntitiesByParamTwo(
                    taskSearchValues.getEmployeeIdTasker()
                    ,taskSearchValues.getTaskProblemId()
                    ,taskSearchValues.getDateBegin()
//                    ,
//                    taskSearchValues.getEmployeeIdExecuter()
                    ,taskSearchValues.getDepartmentIdExecuter()
                    , taskSearchValues.getDataFinish()
                    , taskSearchValues.getStatus()
            );
        }*/
        }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskEntity> getTask(@PathVariable Long id){

//        return taskRepository.getTaskEntityById(275l);
        TaskEntity taskEntity=null;
        try {
            taskEntity=taskServices.findById(id);
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
            taskEntity=taskServices.findByIdGetParent(id);
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
            taskEntity=taskServices.findByIdGetChildren(id);
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
            taskEntity=taskServices.getAllChildren(id);
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
            taskEntity=taskServices.getAllParent(id);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("task with id={} not found",id);
            return new ResponseEntity(String.format("task with id=%s not found",id), HttpStatus.NOT_ACCEPTABLE);


        }

        return ResponseEntity.ok(taskEntity);
    }


}
