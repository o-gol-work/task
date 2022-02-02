package ru.olejkai.task_vsr.services.dbAccessServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.olejkai.task_vsr.controllers.EmployeeController;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.repository.DepartmentRepository;
import ru.olejkai.task_vsr.repository.EmployeeRepository;
import ru.olejkai.task_vsr.search.EmployeeSearchValues;
import ru.olejkai.task_vsr.security.JWTTokenProvider;
import ru.olejkai.task_vsr.security.SecurityConstants;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServices {

    EmployeeRepository employeeRepository;
    JWTTokenProvider jwtTokenProvider;

    public EmployeeServices(EmployeeRepository employeeRepository,JWTTokenProvider jwtTokenProvider) {
        this.employeeRepository = employeeRepository;
        this.jwtTokenProvider=jwtTokenProvider;
    }

    public static final Logger LOG = LoggerFactory.getLogger(EmployeeServices.class);



    public Collection<EmployeeEntity> findBySurname(String surname){
        return employeeRepository.findBySurname(surname);
    }





    public List<EmployeeEntity> findAll(){
        return employeeRepository.findAll();
    }


    public EmployeeEntity findById(Long id){
        return employeeRepository.findById(id).get();

    }



    public Long getCurrentEmployeeId(@NotNull String authorizationToken){
        String bearToken=authorizationToken;
        Long userId=null;
            try {
                if(StringUtils.hasText(bearToken) && bearToken.startsWith(SecurityConstants.TOKEN_PREFIX)){
                    bearToken=bearToken.split(" ")[1];
                    LOG.info(bearToken);

                }
                if(StringUtils.hasText(bearToken)&&jwtTokenProvider.validationToken(bearToken)) {
                    userId = jwtTokenProvider.getUserIdFromToken(bearToken);
                    LOG.info(userId.toString());
                }

            }catch (Exception e){
                e.printStackTrace();
                LOG.error("This employee  not found");

            }

            return userId;

    }




    private EmployeeEntity getEmployeeByPrincipal(Principal principal){
        return employeeRepository.findEmployeeEntityByTabelNumber(Integer.parseInt(principal.getName())).
                orElseThrow(() -> new UsernameNotFoundException(String.format("employee %s not found", principal.getName())));
    }

    public EmployeeEntity getCurrentEmployee(Principal principal){
        return getEmployeeByPrincipal(principal);
    }

    public EmployeeEntity findEmployeeEntityById(Long id){
        return employeeRepository.findEmployeeEntityById(id).get();
    };

    public EmployeeEntity findEmployeeEntityByTabelNumber(Integer tabelNUmber){
        return employeeRepository.findEmployeeEntityByTabelNumber(tabelNUmber).get();
    }



}
