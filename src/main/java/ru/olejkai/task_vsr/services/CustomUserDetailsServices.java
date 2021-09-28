package ru.olejkai.task_vsr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.repository.EmployeeRepository;

public class CustomUserDetailsServices implements UserDetailsService {
    EmployeeRepository employeeRepository;

    @Autowired
    public CustomUserDetailsServices(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeEntity employee=employeeRepository.findEmployeeEntityByTabelNumber(Integer.parseInt(username));



        return null;
    }
}
