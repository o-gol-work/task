package ru.olejkai.task_vsr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.repository.EmployeeRepository;

import java.util.Collection;

@Controller
@RequestMapping("/start/encript")
public class BCriptEncodingController {

    BCryptPasswordEncoder bCryptPasswordEncoder;
    EmployeeRepository employeeRepository;

    @Autowired
    public BCriptEncodingController(BCryptPasswordEncoder bCriptEncodingController, EmployeeRepository employeeRepository) {
        this.bCryptPasswordEncoder = bCriptEncodingController;
        this.employeeRepository = employeeRepository;
    }

    
    

    @GetMapping("/e")
    void encrpt(){
        System.out.println("fuuuuuuuuuuuuuuuuuu");
        Collection<EmployeeEntity> entities=employeeRepository.findAll();

        for (EmployeeEntity employeeEntity :
                entities) {
            employeeEntity.setPassword(bCryptPasswordEncoder.encode(employeeEntity.getPassword()));
            employeeRepository.save(employeeEntity);

        }
    }
}
