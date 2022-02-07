package ru.olejkai.task_vsr.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.olejkai.task_vsr.dto.TaskDto;
import ru.olejkai.task_vsr.entity.TaskEntity;
import ru.olejkai.task_vsr.repository.TaskRepository;
import ru.olejkai.task_vsr.search.TaskSearchValues;
import ru.olejkai.task_vsr.services.dbAccessServices.TaskServices;

import java.util.Collection;

@RestController
@RequestMapping("start/tasks")
public class TaskControllerTest {

    private TaskServices taskServices;
    private TaskRepository taskRepository;

    @Autowired
    public TaskControllerTest(TaskServices taskServices, TaskRepository taskRepository) {
        this.taskServices = taskServices;
        this.taskRepository=taskRepository;
    }



    private static final Logger LOG = LoggerFactory.getLogger(TaskControllerTest.class);


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




    @PostMapping("/search/{id}")
    public ResponseEntity<Page<TaskEntity>> findTaskEntitiesByParamTaskerTest(@RequestBody TaskSearchValues taskSearchValues,
                                                                              @PathVariable Long id){

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

        Page<TaskEntity> result=taskServices.findTaskEntitiesByParamTaskerTest(
                taskSearchValues
                ,pageRequest
                ,id
        );

        return ResponseEntity.ok(result);

    }




    /*@PostMapping("/create")
    public ResponseEntity createTask(@RequestBody TaskEntity taskDto){
        TaskEntity result;
        result=taskServices.createTask(taskDto);
        if (result!=null)
            return ResponseEntity.ok(result);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Запрос не был создан!!");


        *//*Long parentId=taskDto.getParentId()!=null?taskDto.getParentId():null;
        TaskEntity result=null;


        try {
            if(parentId!=null)
                taskRepository.saveChild(parentId,taskDto.getIdEmployeeTasker(),taskDto.getIdProblem());
            else
                taskRepository.saveParent(taskDto.getIdEmployeeTasker(),taskDto.getIdProblem());
        return ResponseEntity.ok(result);
        }catch (Exception e){

            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Возможно в не имеете право создовать данный запрос");
        }*//*




    }
*/




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




    @GetMapping("/task/dto/{id}")
    public void getTaskDto(@PathVariable Long id){

//        return taskRepository.getTaskEntityById(275l);

        try {
            TaskDto taskEntity=taskServices.findTaskDto(id);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("task with id={} not found",id);



        }


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
