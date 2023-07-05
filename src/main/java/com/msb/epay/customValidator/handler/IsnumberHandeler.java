package com.msb.epay.customValidator.handler;

import com.msb.epay.customValidator.IsNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class IsnumberHandeler implements ConstraintValidator<IsNumber, String> {
    private String message;
    private Class<?>[] groups;
    private Class<? extends Payload>[] payload;
    private List<String> values;

    @Override
    public void initialize(IsNumber isNumber) {
        this.message = isNumber.message();
        this.values = Arrays.asList(isNumber.values());
    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try{
            BigDecimal number = new BigDecimal(s);
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(this.values.toString().concat(message))
                    .addConstraintViolation();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
