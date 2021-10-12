package ru.olejkai.task_vsr.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.olejkai.task_vsr.entity.DepartmentEntity;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.repository.DepartmentRepository;
import ru.olejkai.task_vsr.repository.EmployeeRepository;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/start/departments")
public class DepartmentController {

    private DepartmentRepository departmentRepository;

    @Autowired
    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    private static final Logger LOG = LoggerFactory.getLogger(DepartmentController.class);


    @GetMapping("/all_departments")
    public ResponseEntity<Collection<DepartmentEntity>> getAllDepartments(){
        return ResponseEntity.ok(departmentRepository.findAllByOrderByTitleAsc());
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<DepartmentEntity> getDepartment(@PathVariable Long id){
        DepartmentEntity departmentEntity=null;
        try {
            departmentEntity=departmentRepository.findById(id).get();
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("department with id={} not found",id);
            return new ResponseEntity(String.format("department with id=%s not found",id), HttpStatus.NOT_ACCEPTABLE);


        }

        return ResponseEntity.ok(departmentEntity);
    }

    @GetMapping("/parent_department/{id}")
    public ResponseEntity<DepartmentEntity> getParent(@PathVariable Long id){
        DepartmentEntity departmentEntity=null;
        try {
            departmentEntity= departmentRepository.findById(id).get().getParent();
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("department with id={} not found",id);
            return new ResponseEntity(String.format("department with id=%s not found",id), HttpStatus.NOT_ACCEPTABLE);


        }

        return ResponseEntity.ok( departmentEntity);
    }

    @GetMapping("/children_first_line_departments/{id}")
    public ResponseEntity<Collection<DepartmentEntity>> getChildren(@PathVariable Long id){
        Collection<DepartmentEntity> departmentEntity=null;
        try {
            departmentEntity= departmentRepository.findById(id).get().getChildren();
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("department with id={} not found",id);
            return new ResponseEntity(String.format("department with id=%s not found",id), HttpStatus.NOT_ACCEPTABLE);


        }

        return ResponseEntity.ok( departmentEntity);
    }

    @GetMapping("/all_children__departments/{id}")
    public ResponseEntity<Collection<DepartmentEntity>> getAllChildren(@PathVariable Long id){
        Collection<DepartmentEntity> departmentEntity=null;
        try {
            departmentEntity= departmentRepository.getAllChildren(id);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("department with id={} not found",id);
            return new ResponseEntity(String.format("department with id=%s not found",id), HttpStatus.NOT_ACCEPTABLE);


        }

        return ResponseEntity.ok( departmentEntity);
    }

    @GetMapping("/all_parent_departments/{id}")
    public ResponseEntity<Collection<DepartmentEntity>> getAllParent(@PathVariable Long id) {
        Collection<DepartmentEntity> departmentEntity=null;
        try {
            departmentEntity= departmentRepository.getAllParent(id);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("department with id={} not found",id);
            return new ResponseEntity(String.format("department with id=%s not found",id), HttpStatus.NOT_ACCEPTABLE);


        }

        return ResponseEntity.ok( departmentEntity);
    }



}
