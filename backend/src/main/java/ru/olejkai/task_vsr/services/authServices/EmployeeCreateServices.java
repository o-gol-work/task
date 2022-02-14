package ru.olejkai.task_vsr.services.authServices;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.entity.EmployeeRoleEntity;
import ru.olejkai.task_vsr.exeptions.EmployeeEntityExistExeption;
import ru.olejkai.task_vsr.payload.request.SignupRequest;
import ru.olejkai.task_vsr.repository.EmployeeRepository;
import ru.olejkai.task_vsr.repository.EmployeeRoleEntityRepository;

@Slf4j
@Service
public class EmployeeCreateServices {
    public static final Logger LOG = LoggerFactory.getLogger(EmployeeCreateServices.class);
    private EmployeeRepository employeeRepository;
    private EmployeeRoleEntityRepository employeeRoleEntityRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EmployeeCreateServices(EmployeeRepository employeeRepository, BCryptPasswordEncoder bCryptPasswordEncoder,EmployeeRoleEntityRepository employeeRoleEntityRepository) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.employeeRoleEntityRepository=employeeRoleEntityRepository;
    }

    public EmployeeEntity createEmployee(SignupRequest employee)  {
        EmployeeEntity employeeEntity= new EmployeeEntity();
        employeeEntity.setTabelNumber(Integer.parseInt(employee.getTabelNumber()));
        employeeEntity.setName(employee.getName());
        employeeEntity.setSurname(employee.getSurname());
        employeeEntity.setTelephoneNumber(employee.getTelephoneNumber());
        employeeEntity.setPostHasDepartmentId(Long.parseLong(employee.getPostHasDepartmentId()));
        employeeEntity.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employeeEntity.setWorked((byte) 1);
        try{
            LOG.info("Save Employee {}",employee.getTabelNumber());
            employeeEntity= employeeRepository.save(employeeEntity);
            employeeRoleEntityRepository.save(new EmployeeRoleEntity("ROLE_USER",employeeEntity.getId()));
            return employeeEntity;
        }catch (Exception  e){
            LOG.error("Registr Employee error {}", e.getMessage());
            throw new EmployeeEntityExistExeption(employeeEntity.getName());

        }
    }


}
