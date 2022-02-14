package ru.olejkai.task_vsr.services.authServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.entity.EmployeeRoleEntity;
import ru.olejkai.task_vsr.repository.EmployeeRepository;
import ru.olejkai.task_vsr.repository.EmployeeRoleEntityRepository;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsServices implements UserDetailsService {
    EmployeeRepository employeeRepository;
    EmployeeRoleEntityRepository employeeRoleEntityRepository;

    @Autowired
    public CustomUserDetailsServices(EmployeeRepository employeeRepository,
                                     EmployeeRoleEntityRepository employeeRoleEntityRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeRoleEntityRepository=employeeRoleEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeEntity employee=employeeRepository.findEmployeeEntityByTabelNumber(Integer.parseInt(username))
                .orElseThrow(()->new UsernameNotFoundException(String.format("User %s not found",username)));
        EmployeeEntity employeeDetail=build(employee);
        return employeeDetail;
    }


    public EmployeeEntity loadUserById(Long id) throws UsernameNotFoundException {

        return build(Objects.requireNonNull(employeeRepository.findById(id).orElse(null)));

    }




    private EmployeeEntity build(EmployeeEntity employee){
        Collection<EmployeeRoleEntity> roleEmp=employeeRoleEntityRepository.findEmployeeRoleEntityByEmployeeId(employee.getId());
        Collection<GrantedAuthority> roles=employeeRoleEntityRepository.findEmployeeRoleEntityByEmployeeId(employee.getId()).stream()
                .map(role-> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
        EmployeeEntity emplTast=new EmployeeEntity(
                employee.getId(),
                Integer.toString(employee.getTabelNumber()),
                employee.getName(),
                employee.getSurname(),
                employee.getPassword(),
                roles,
                roleEmp);

        return  emplTast;


    }
}
