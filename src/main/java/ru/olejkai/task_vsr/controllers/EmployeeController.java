package ru.olejkai.task_vsr.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.repository.EmployeeRepository;
import ru.olejkai.task_vsr.search.EmployeeSearchValues;
import ru.olejkai.task_vsr.services.authServices.CustomUserDetailsServices;
import ru.olejkai.task_vsr.services.dbAccessServices.EmployeeServices;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/start/employees")
//@RequestMapping
public class EmployeeController {

    private EmployeeServices employeeServices;
    private CustomUserDetailsServices customUserDetailsServices;
    public static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    public EmployeeController(EmployeeServices employeeServices, CustomUserDetailsServices customUserDetailsServices) {
        this.employeeServices = employeeServices;
        this.customUserDetailsServices = customUserDetailsServices;
    }

    @PostMapping("/find_empl")
    public ResponseEntity<Collection<EmployeeEntity>> findBySurname(@RequestBody EmployeeSearchValues surname1){
        return ResponseEntity.ok(employeeServices.findBySurname(surname1.getEmployeeSurname()));
    }

    @PostMapping("/find_empl_surn/")
    public ResponseEntity<Collection<String>> findBySurnameText(@RequestBody EmployeeSearchValues surname1){
        String surname=surname1.getEmployeeSurname();
        return ResponseEntity.ok(employeeServices.findBySurname(surname).stream().map(entity->entity.getSurname()).collect(Collectors.toList()));
    }

    @GetMapping("/all_employees")
    public List<EmployeeEntity> allEmployees(){
        return employeeServices.findAll();
    }

//    @GetMapping("/api/auth/employee")
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeEntity> employee(@PathVariable Long id){
        EmployeeEntity employeeEntity=null;
        try {
            employeeEntity=employeeServices.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("employee with id={} not found",id);
            return new ResponseEntity(String.format("employee with id=%s not found",id), HttpStatus.NOT_ACCEPTABLE);


        }

        return ResponseEntity.ok( employeeServices.findById(id));
    }
}
