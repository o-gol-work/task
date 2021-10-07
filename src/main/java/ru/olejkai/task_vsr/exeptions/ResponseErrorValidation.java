package ru.olejkai.task_vsr.exeptions;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseErrorValidation {

    public ResponseEntity<Object> mapValidationServices(BindingResult bindResult){

        if(bindResult.hasErrors()){

            Map<String,String> errors=new  HashMap<>();

            if(!CollectionUtils.isEmpty(bindResult.getAllErrors())){
                for (ObjectError error:
                        bindResult.getAllErrors()) {
                    errors.put(error.getCode(),error.getDefaultMessage());

                }
            }

            if(!CollectionUtils.isEmpty(bindResult.getFieldErrors())){
                for (FieldError error:
                    bindResult.getFieldErrors() ) {
                    errors.put(error.getField(),error.getDefaultMessage());

                }

            }

            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }

        return null;

    }

}
