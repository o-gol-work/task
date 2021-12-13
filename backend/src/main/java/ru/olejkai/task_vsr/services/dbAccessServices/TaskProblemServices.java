package ru.olejkai.task_vsr.services.dbAccessServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.olejkai.task_vsr.entity.TaskProblemEntity;
import ru.olejkai.task_vsr.repository.TaskProblemRepository;
import ru.olejkai.task_vsr.repository.TaskRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Collection;

@Service
@Transactional
public class TaskProblemServices {
    TaskProblemRepository taskProblemRepository;
    private EmployeeServices employeeServices;

    @Autowired
    public TaskProblemServices(TaskProblemRepository taskProblemRepository, EmployeeServices employeeServices) {
        this.taskProblemRepository = taskProblemRepository;
        this.employeeServices=employeeServices;
    }

    private static final Logger LOG = LoggerFactory.getLogger(TaskProblemServices.class);


    public Collection<TaskProblemEntity> findTaskProblemByEmployeeTaskerId(Long employeeTaskerId)
    {
        return taskProblemRepository.getTasksByEmployeeTaskerId(employeeTaskerId);
    }

    public Collection<TaskProblemEntity> findTaskProblemByEmployeeTaskerId(Principal principal)
    {
        return taskProblemRepository.getTasksByEmployeeTaskerId(employeeServices.getCurrentEmployee(principal).getId()!=null?employeeServices.getCurrentEmployee(principal).getId():null);
    }

}
