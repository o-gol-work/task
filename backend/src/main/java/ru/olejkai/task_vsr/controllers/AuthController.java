package ru.olejkai.task_vsr.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.exeptions.ResponseErrorValidation;
import ru.olejkai.task_vsr.payload.request.LoginRequest;
import ru.olejkai.task_vsr.payload.request.SignupRequest;
import ru.olejkai.task_vsr.payload.responce.JWTTokenSuccessResponse;
import ru.olejkai.task_vsr.payload.responce.MessageResponse;
import ru.olejkai.task_vsr.security.JWTTokenProvider;
import ru.olejkai.task_vsr.security.SecurityConstants;
import ru.olejkai.task_vsr.services.authServices.EmployeeCreateServices;
import ru.olejkai.task_vsr.services.dbAccessServices.EmployeeServices;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/start")
@PreAuthorize("permitAll()")
public class AuthController {

    public static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private EmployeeServices employeeServices;
    private ResponseErrorValidation responseErrorValidation;
    private EmployeeCreateServices employeeCreateServices;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(ResponseErrorValidation responseErrorValidation, EmployeeCreateServices employeeCreateServices, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider,EmployeeServices employeeServices) {
        this.responseErrorValidation = responseErrorValidation;
        this.employeeCreateServices = employeeCreateServices;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.employeeServices=employeeServices;
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateEmployee(
            @Valid @RequestBody LoginRequest loginRequest
            , BindingResult bindingResult)  {
        LOG.info("Signin start");
        ResponseEntity<Object> responseEntity = responseErrorValidation.mapValidationServices(bindingResult);
        if (!ObjectUtils.isEmpty(responseEntity)) return responseEntity;
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
//        EmployeeEntity employeeEntity=employeeServices.findEmployeeEntityByTabelNumber(Integer.getInteger(loginRequest.getUsername()));
        EmployeeEntity employeeEntity=(EmployeeEntity) authentication.getPrincipal();
        LOG.info("Singin end");
        System.out.println("fuuuuuuuuuuuuuuuuuuuuu end");
        JWTTokenSuccessResponse jwtTokenSuccessResponse=new JWTTokenSuccessResponse(true, jwt,employeeEntity);
        return ResponseEntity.ok(jwtTokenSuccessResponse);




    }



    @PostMapping("/signup")
    public ResponseEntity<Object> registerEmployee(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult)  {
        LOG.info("Signout start");

        ResponseEntity<Object> responseEntity=responseErrorValidation.mapValidationServices(bindingResult);
        if(!ObjectUtils.isEmpty(responseEntity)) return responseEntity;

        employeeCreateServices.createEmployee(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User register successfully"));
    }
}
