package com.mitchell.claims.domain.validator;

import com.mitchell.claims.domain.LossInfo;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * @author Khaled Ayoubi
 */
public class LossInfoValidator implements ConstraintValidator<ValidLossInfo, LossInfo> {
    private static final List<String> validCodes = Arrays.asList("Collision", "Explosion", "Fire", "Hail", "Mechanical Breakdown", "Other");

    @Override
    public void initialize(ValidLossInfo validLossInfo) {

    }

    @Override
    public boolean isValid(LossInfo s, ConstraintValidatorContext constraintValidatorContext) {
        return s == null || validCodes.contains(s.getCauseOfLoss());
    }
}
