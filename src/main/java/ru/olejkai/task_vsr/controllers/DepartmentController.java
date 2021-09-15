package ru.olejkai.task_vsr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.olejkai.task_vsr.entity.DepartmentEntity;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.repository.DepartmentRepository;
import ru.olejkai.task_vsr.repository.EmployeeRepository;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private DepartmentRepository departmentRepository;

    @Autowired
    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @GetMapping("/department")
    public void getDepartment(){
        DepartmentEntity dep= departmentRepository.getDepartmentEntityById(1l);
    }

    @GetMapping("/parent_department")
    public DepartmentEntity getParent(){
        return departmentRepository.getDepartmentEntityById(12l).getParent();
    }

    @GetMapping("/children_first_line_departments")
    public Collection<DepartmentEntity> getChildren(){
        return departmentRepository.getDepartmentEntityById(1l).getChildren();
    }

    @GetMapping("/all_children__departments")
    public Collection<DepartmentEntity> getAllChildren(){
        return departmentRepository.getAllChildren(1l);
    }

    @GetMapping("/all_parent_departments")
    public Collection<DepartmentEntity> getAllParent() {
        return departmentRepository.getAllParent(12l);
    }



}
