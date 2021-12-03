package ru.olejkai.task_vsr.services.dbAccessServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.AbstractJpaQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.olejkai.task_vsr.controllers.DepartmentController;
import ru.olejkai.task_vsr.dto.TaskDto;
import ru.olejkai.task_vsr.entity.TaskEntity;
import ru.olejkai.task_vsr.repository.TaskRepository;
import ru.olejkai.task_vsr.search.TaskSearchValues;

import java.security.Principal;
import java.util.Collection;
@Service
@Transactional
public class TaskServices {

    TaskRepository taskRepository;
    EmployeeServices employeeServices;


    @Autowired
    public TaskServices(TaskRepository taskRepository, EmployeeServices employeeServices) {
        this.taskRepository = taskRepository;
        this.employeeServices= employeeServices;
    }

    private static final Logger LOG = LoggerFactory.getLogger(TaskServices.class);



    public Collection<TaskEntity> findAll(){
        return taskRepository.findAll();
    }


    public void createTaskParent(Long taskProblemID, Principal principal){
        Long employeeIdTasker=employeeServices.getCurrentEmployee(principal).getId();
        taskRepository.saveParent(taskProblemID,employeeIdTasker);

    }

    public void createTaskChild(Long parentId,Long taskProblemID, Principal principal){
        Long employeeIdTasker=employeeServices.getCurrentEmployee(principal).getId();
        taskRepository.saveChild(parentId,taskProblemID,employeeIdTasker);

    }


    public Page<TaskEntity> findTaskEntitiesByParamTasker(TaskSearchValues taskSearchValues, Pageable pageRequest, Principal principal) {
        Long employeeIdTasker = employeeServices.getCurrentEmployee(principal).getId();
        return taskRepository.findTaskEntitiesByParamTasker(
                employeeIdTasker
                , taskSearchValues.getEmployeeIdTasker()
                , taskSearchValues.getTaskProblemId()
                , taskSearchValues.getDateBegin()
                ,
                taskSearchValues.getEmployeeIdExecuter()
                , taskSearchValues.getDepartmentIdExecuter()
                , taskSearchValues.getDataFinish()
                , taskSearchValues.getStatus()
                , pageRequest
        );
    }



    public Page<TaskEntity> findTaskEntitiesByParamDepartmentExecuter(TaskSearchValues taskSearchValues, Pageable pageRequest, Principal principal) {
        Long departmentIdExecuter=employeeServices.getCurrentEmployee(principal).getPostHasDepartmentByPostHasDepartmentId().getDepartmentId();
        return taskRepository.findTaskEntitiesByParamDepartmentExecuter(
                departmentIdExecuter
                , taskSearchValues.getEmployeeIdTasker()
                , taskSearchValues.getTaskProblemId()
                , taskSearchValues.getDateBegin()
                ,
                taskSearchValues.getEmployeeIdExecuter()
                , taskSearchValues.getDepartmentIdExecuter()
                , taskSearchValues.getDataFinish()
                , taskSearchValues.getStatus()
                , pageRequest
        );
    }



    public Page<TaskEntity> findTaskEntitiesByParamTaskerTest(TaskSearchValues taskSearchValues, Pageable pageRequest, Long id) {
//        Long employeeIdTasker = employeeServices.getCurrentEmployee(principal).getId();
        return taskRepository.findTaskEntitiesByParamTasker(
                id
                , taskSearchValues.getEmployeeIdTasker()
                , taskSearchValues.getTaskProblemId()
                , taskSearchValues.getDateBegin()
                ,
                taskSearchValues.getEmployeeIdExecuter()
                , taskSearchValues.getDepartmentIdExecuter()
                , taskSearchValues.getDataFinish()
                , taskSearchValues.getStatus()
                , pageRequest
        );
    }






    public Page<TaskEntity> findTaskEntitiesByParamOne(TaskSearchValues taskSearchValues, Pageable pageRequest){

       return taskRepository.findTaskEntitiesByParamOne(
                taskSearchValues.getEmployeeIdTasker()
                ,taskSearchValues.getTaskProblemId()
                ,taskSearchValues.getDateBegin()
                ,
                taskSearchValues.getEmployeeIdExecuter()
                ,taskSearchValues.getDepartmentIdExecuter()
                , taskSearchValues.getDataFinish()
                , taskSearchValues.getStatus()
                ,pageRequest
        );



    }








    public TaskEntity findById(Long id){
        return taskRepository.findById(id).get();
    }




    public TaskDto findTaskDto(Long id){
        return taskRepository.findTaskDto(id).get();
    }






    public TaskEntity findByIdGetParent(Long id){
            return taskRepository.findDirectParentByChildId(id).get();
//            return taskRepository.findById(id).get().getParent();
    }


    public Collection<TaskEntity> findByIdGetChildren( Long id){
        return taskRepository.findById(id).get().getChildren();

    }


    public Collection<TaskEntity> getAllChildren(Long id){
        return taskRepository.getAllChildren(id);

    }


    public Collection<TaskEntity> getAllParent(Long id) {
        return taskRepository.getAllParent(id);

    }
}
