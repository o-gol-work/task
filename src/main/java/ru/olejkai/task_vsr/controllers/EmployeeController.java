package ru.olejkai.task_vsr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/employee")
    public EmployeeEntity employee(){
//        return employeeRepository.getById(3l);
        /*EmployeeEntity employee=employeeRepository.findEmployeeEntityByTabelNumber(1111).get();
        UserDetails userDetails=customUserDetailsServices.loadUserByUsername("1111");*/

        return employeeRepository.findById(19l).get();
    }
}
