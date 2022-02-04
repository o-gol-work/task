package ru.olejkai.task_vsr.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.olejkai.task_vsr.dto.TaskDto;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.entity.TaskEntity;
import ru.olejkai.task_vsr.search.TaskSearchValues;
import ru.olejkai.task_vsr.services.dbAccessServices.TaskServices;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private TaskServices taskServices;


    @Autowired
    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }



    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);





    @PostMapping("/search")
    public ResponseEntity<Page<TaskEntity>> findTaskEntitiesByParamTaskerTest(@RequestBody TaskSearchValues taskSearchValues,
                                                                              Principal principal){

        LOG.info(principal.toString());
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



        Page<TaskEntity> result=taskServices.findTaskEntitiesByParamTasker(
                taskSearchValues
                ,pageRequest
                ,principal
        );

        return ResponseEntity.ok(result);

    }



    @PostMapping("/searchMyTask")
    public ResponseEntity<Page<TaskEntity>> findTaskEntitiesByParamCurrentTasker(@RequestBody TaskSearchValues taskSearchValues,
                                                                              Principal principal){

        LOG.info(principal.toString());
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



        Page<TaskEntity> result=taskServices.findTaskEntitiesByParamTasker(
                taskSearchValues
                ,pageRequest
                ,principal
        );

        return ResponseEntity.ok(result);

    }


    @PostMapping("/searcTaskForMyDep")
    public ResponseEntity<Page<TaskEntity>> findTaskEntitiesByParamCurrentDep(@RequestBody TaskSearchValues taskSearchValues,
                                                                                 Principal principal){

        LOG.info(principal.toString());
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



        Page<TaskEntity> result=taskServices.findTaskEntitiesByParamDepartmentExecuter(
                taskSearchValues
                ,pageRequest
                ,principal
        );

        return ResponseEntity.ok(result);

    }


    @PostMapping("/create")
    public ResponseEntity createTask(@RequestBody TaskDto taskDto,
                                     Principal principal){
        TaskEntity result;
        result=taskServices.createTask(taskDto,principal);
        if (result!=null)
            return ResponseEntity.ok(result);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Запрос не был создан!!");






    }

    @GetMapping("/all_tasks")
    public Collection<TaskEntity> taskEntities(){
        return taskServices.findAll();
    }







}
