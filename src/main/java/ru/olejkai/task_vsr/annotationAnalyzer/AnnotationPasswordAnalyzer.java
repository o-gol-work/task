package ru.olejkai.task_vsr.annotationAnalyzer;

import org.hibernate.mapping.Constraint;
import ru.olejkai.task_vsr.annotation.PasswordMatchers;
import ru.olejkai.task_vsr.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AnnotationPasswordAnalyzer implements ConstraintValidator<PasswordMatchers,Object> {

    @Override
    public void initialize(PasswordMatchers passwordMatchers) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        SignupRequest signupRequest=(SignupRequest) value;
        return signupRequest.getPassword().equals(signupRequest.getConfirmPassword());
    }
}
