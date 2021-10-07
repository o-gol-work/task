package ru.olejkai.task_vsr.services;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.entity.EmployeeRoleEntity;
import ru.olejkai.task_vsr.exeptions.EmployeeEntityExistExeption;
import ru.olejkai.task_vsr.payload.request.SignupRequest;
import ru.olejkai.task_vsr.repository.EmployeeRepository;
import ru.olejkai.task_vsr.repository.EmployeeRoleEntityRepository;
import ru.olejkai.task_vsr.security.JWTTokenProvider;

@Slf4j
@Service
public class EmployeeCreateServices {
    public static final Logger LOG = LoggerFactory.getLogger(EmployeeCreateServices.class);
    private EmployeeRepository employeeRepository;
    EmployeeRoleEntityRepository employeeRoleEntityRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EmployeeCreateServices(EmployeeRepository employeeRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public EmployeeEntity createEmployee(SignupRequest employee) throws EmployeeEntityExistExeption {
        EmployeeEntity employeeEntity= new EmployeeEntity();
        employeeEntity.setTabelNumber(employee.getTabelNumber());
        employeeEntity.setName(employee.getName());
        employeeEntity.setSurname(employee.getSurname());
//        employeeEntity.setTelephoneNumber(employee.getTelephoneNumber());
        employeeEntity.getEmployeeRolesById().add(new EmployeeRoleEntity("ROLE_USER"));
        employeeEntity.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employeeEntity.setWorked((byte) 1);
        try{
            LOG.info("Save Employee {}",employee.getTabelNumber());
//            employeeRoleEntityRepository.save(new EmployeeRoleEntity("ROLE_USER",employeeEntity.getId()));
            return employeeEntity= employeeRepository.save(employeeEntity);
        }catch (Exception  e){
            LOG.error("Registr Employee error {}", e.getMessage());
            throw new EmployeeEntityExistExeption(employeeEntity.getName());

        }
    }


}
