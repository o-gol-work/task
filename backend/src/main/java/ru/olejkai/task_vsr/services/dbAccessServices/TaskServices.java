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
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.olejkai.task_vsr.controllers.DepartmentController;
import ru.olejkai.task_vsr.dto.TaskDto;
import ru.olejkai.task_vsr.dto.TaskDtoCreate;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.entity.TaskEntity;
import ru.olejkai.task_vsr.repository.TaskRepository;
import ru.olejkai.task_vsr.search.TaskSearchValues;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

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


    /*public void createTaskParent(Long taskProblemID, Principal principal){
        Long employeeIdTasker=employeeServices.getCurrentEmployee(principal).getId();
        taskRepository.saveParent(taskProblemID,employeeIdTasker);

    }

    public void createTaskChild(Long parentId,Long taskProblemID, Principal principal){
        Long employeeIdTasker=employeeServices.getCurrentEmployee(principal).getId();
        taskRepository.saveChild(parentId,taskProblemID,employeeIdTasker);

    }*/


    public Page<TaskEntity> findTaskEntitiesByParamTasker(TaskSearchValues taskSearchValues, Pageable pageRequest, Principal principal) {
        Long employeeIdTasker = employeeServices.getCurrentEmployee(principal).getId()!=null?employeeServices.getCurrentEmployee(principal).getId():null;
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
        EmployeeEntity employeeEntity=employeeServices.getCurrentEmployee(principal);
        Long departmentIdExecuter=employeeEntity.getPostHasDepartmentByPostHasDepartmentId().getDepartmentByDepartmentId().getId();
        Page<TaskEntity> tasks=taskRepository.findTaskEntitiesByParamDepartmentExecuter(
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
        return tasks;
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


    @Transactional
    public TaskEntity createTask(TaskDtoCreate taskEntity, Principal principal){
        TaskEntity tse=new TaskEntity();
        tse.setEmployeeIdTasker(employeeServices.getCurrentEmployee(principal).getId()!=null?employeeServices.getCurrentEmployee(principal).getId():null);
        tse.setTaskProblemId(taskEntity.getIdProblem());
        tse.setParentId(taskEntity.getParentId());
        tse=taskRepository.findTaskByIdTesting(taskRepository.saveAndFlush(tse).getId());
        return tse;
    }


    @Transactional
    public TaskEntity createTaskSocKet(TaskEntity taskEntity){
        return taskRepository.saveAndFlush(taskEntity);
    }




    /*@Transactional
    public TaskEntity createTask(TaskDto taskDto, Principal principal){
        Long parentId=taskDto.getParentId()!=null?taskDto.getParentId():null;
        Long employeeIdTasker=employeeServices.getCurrentEmployee(principal).getId()!=null?employeeServices.getCurrentEmployee(principal).getId():null;
        TaskEntity result=null;


        try {
            if(parentId!=null)
                result=taskRepository.saveChild(parentId,employeeIdTasker,taskDto.getIdProblem()).orElseThrow();
            else
                result=taskRepository.saveParent(employeeIdTasker,taskDto.getIdProblem()).orElseThrow();
//            return taskRepository.findLastByTasker(taskDto.getIdEmployeeTasker());
            return result;
        }catch (Exception e){

            LOG.error(e.getLocalizedMessage());
            return null;
        }
    }*/


    /*@Transactional
    public TaskEntity createTask(TaskDto taskDto, Principal principal){
        Long parentId=taskDto.getParentId()!=null?taskDto.getParentId():null;
        Long employeeIdTasker=employeeServices.getCurrentEmployee(principal).getId()!=null?employeeServices.getCurrentEmployee(principal).getId():null;
//        TaskEntity result=null;


        try {
            if(parentId!=null)
                taskRepository.saveChild(parentId,employeeIdTasker,taskDto.getIdProblem());
            else
                taskRepository.saveParent(employeeIdTasker,taskDto.getIdProblem());
            return taskRepository.findLastByTasker(taskDto.getIdEmployeeTasker());
        }catch (Exception e){

            LOG.error(e.getLocalizedMessage());
            return null;
        }
    }*/

    /*@Transactional
    public TaskEntity createTaskTest(TaskDto taskDto){
        Long parentId=taskDto.getParentId()!=null?taskDto.getParentId():null;
//        TaskEntity result=null;


        try {
            if(parentId!=null)
                taskRepository.saveChild(parentId,taskDto.getIdEmployeeTasker(),taskDto.getIdProblem());
            else
                taskRepository.saveParent(taskDto.getIdEmployeeTasker(),taskDto.getIdProblem());
            return taskRepository.findLastByTasker(taskDto.getIdEmployeeTasker());
        }catch (Exception e){

            LOG.error(e.getLocalizedMessage());
            return null;
        }
    }*/






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


    public TaskEntity findByIdTesting(Long id){
        return taskRepository.findTaskByIdTesting(id);
    }

}
