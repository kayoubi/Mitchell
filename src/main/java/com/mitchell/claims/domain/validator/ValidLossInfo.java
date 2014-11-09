package com.mitchell.claims.domain.validator;

import com.mitchell.claims.domain.LossInfo;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Khaled Ayoubi
 */
@Documented
@Constraint(validatedBy = LossInfoValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLossInfo {
    String message() default "{CauseOfLossCode}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

