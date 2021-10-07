package ru.olejkai.task_vsr.controllers;

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
import ru.olejkai.task_vsr.exeptions.EmployeeEntityExistExeption;
import ru.olejkai.task_vsr.exeptions.ResponseErrorValidation;
import ru.olejkai.task_vsr.payload.request.LoginRequest;
import ru.olejkai.task_vsr.payload.request.SignupRequest;
import ru.olejkai.task_vsr.payload.responce.JWTTokenSuccessResponse;
import ru.olejkai.task_vsr.payload.responce.MessageResponse;
import ru.olejkai.task_vsr.security.JWTTokenProvider;
import ru.olejkai.task_vsr.security.SecurityConstants;
import ru.olejkai.task_vsr.services.EmployeeCreateServices;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/start")
@PreAuthorize("permitAll()")
public class AuthController {


    private ResponseErrorValidation responseErrorValidation;
    private EmployeeCreateServices employeeCreateServices;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(ResponseErrorValidation responseErrorValidation, EmployeeCreateServices employeeCreateServices, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.responseErrorValidation = responseErrorValidation;
        this.employeeCreateServices = employeeCreateServices;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateEmployee(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult)  {
        ResponseEntity<Object> responseEntity = responseErrorValidation.mapValidationServices(bindingResult);
        if (!ObjectUtils.isEmpty(responseEntity)) return responseEntity;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));




    }



    @PostMapping("/signup")
    public ResponseEntity<Object> registerEmployee(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult)  {
        ResponseEntity<Object> responseEntity=responseErrorValidation.mapValidationServices(bindingResult);
        if(!ObjectUtils.isEmpty(responseEntity)) return responseEntity;

        employeeCreateServices.createEmployee(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User register successfully"));
    }
}
