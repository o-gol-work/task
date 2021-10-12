package ru.olejkai.task_vsr.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.repository.EmployeeRepository;
import ru.olejkai.task_vsr.services.CustomUserDetailsServices;

import java.util.List;

@RestController
@RequestMapping("start/employees")
//@RequestMapping
public class EmployeeController {

    private EmployeeRepository employeeRepository;
    private CustomUserDetailsServices customUserDetailsServices;
    public static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, CustomUserDetailsServices customUserDetailsServices) {
        this.employeeRepository = employeeRepository;
        this.customUserDetailsServices = customUserDetailsServices;
    }

    @GetMapping("/all_employees")
    public List<EmployeeEntity> allEmployees(){
        return employeeRepository.findAll();
    }

//    @GetMapping("/api/auth/employee")
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeEntity> employee(@PathVariable Long id){
        EmployeeEntity employeeEntity=null;
        try {
            employeeEntity=employeeRepository.findById(id).get();
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("employee with id={} not found",id);
            return new ResponseEntity(String.format("employee with id=%s not found",id), HttpStatus.NOT_ACCEPTABLE);


        }

        return ResponseEntity.ok( employeeRepository.findById(id).get());
    }
}
