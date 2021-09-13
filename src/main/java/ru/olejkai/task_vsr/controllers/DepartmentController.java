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
    /*private DepartmentEntity departmentEntity;

    @Autowired
    public void setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }*/

    @Autowired
    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /*@GetMapping("/all_child_departments")
//    public void allEmployees(){
    public Collection<DepartmentEntity> test01(){
        return departmentRepository.getDepartmentsById(11l);
//        return departmentRepository.getDepartmentEntitiesByParentId(2L);
//        List<EmployeeEntity> employees=employeeRepository.findAll();
//        System.out.println(employees);

    }*/




    @GetMapping("/parent_department")
//    public void allEmployees(){
    public String test02(){
        return departmentRepository.getDepartmentEntityById(12l).toString();
//        Long id=  departmentRepository.getDepartmentEntityById(12l).getId();
//        return departmentRepository.getDepartmentEntityById(12l).getId();
//        System.out.println(id);
//        return departmentRepository.getDepartmentEntityById(12l);

//        List<EmployeeEntity> employees=employeeRepository.findAll();

    }

    @GetMapping("/children_departments")
//    public void getAllChildren(){
    public Collection<DepartmentEntity> getAllChildren(){
        /*Collection<DepartmentEntity> childrenDepartments=departmentRepository.getAllChildren(3l);
        System.out.println(childrenDepartments);*/
        return departmentRepository.getAllChildren(3l);


    }

}
