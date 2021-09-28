package ru.olejkai.task_vsr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.repository.EmployeeRepository;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/all_employees")
    public List<EmployeeEntity> allEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/employee")
    public EmployeeEntity employee(){
//        return employeeRepository.getById(3l);
        EmployeeEntity employee=employeeRepository.findEmployeeEntityByTabelNumber(1111);

        return employeeRepository.findById(3l).get();
    }
}
