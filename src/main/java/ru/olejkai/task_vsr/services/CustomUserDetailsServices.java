package ru.olejkai.task_vsr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.repository.EmployeeRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsServices implements UserDetailsService {
    EmployeeRepository employeeRepository;

    @Autowired
    public CustomUserDetailsServices(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeEntity employee=employeeRepository.findEmployeeEntityByTabelNumber(Integer.parseInt(username))
                .orElseThrow(()->new UsernameNotFoundException(String.format("User %s not found",username)));




        return build(employee);
    }


    public EmployeeEntity loadUserById(Long id) throws UsernameNotFoundException {
        return employeeRepository.findById(id)
                .orElse(null);

    }




    private EmployeeEntity build(EmployeeEntity employee){
        Collection<GrantedAuthority> roles=employee.getEmployeeRolesById().stream()
                .map(role-> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());

        return  new EmployeeEntity(
                employee.getId(),
                employee.getUsername(),
                employee.getName(),
                employee.getSurname(),
                employee.getPassword(),
                roles
        );

    }
}
