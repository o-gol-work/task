package ru.olejkai.task_vsr.services.dbAccessServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.olejkai.task_vsr.controllers.EmployeeController;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.repository.DepartmentRepository;
import ru.olejkai.task_vsr.repository.EmployeeRepository;
import ru.olejkai.task_vsr.search.EmployeeSearchValues;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServices {

    EmployeeRepository employeeRepository;

    public EmployeeServices(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public static final Logger LOG = LoggerFactory.getLogger(EmployeeServices.class);



    public Collection<EmployeeEntity> findBySurname(String surname){
        return employeeRepository.findBySurname(surname);
    }





    public List<EmployeeEntity> findAll(){
        return employeeRepository.findAll();
    }


    public EmployeeEntity findById(Long id){
        return employeeRepository.findById(id).get();

    }



}
