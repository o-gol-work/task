package ru.olejkai.task_vsr.annotation;

import ru.olejkai.task_vsr.annotationAnalyzer.AnnotationPasswordAnalyzer;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AnnotationPasswordAnalyzer.class)
public @interface PasswordMatchers {
    String message()  default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
