package com.msb.epay.customValidator;

import com.msb.epay.customValidator.handler.IsnumberHandeler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsnumberHandeler.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsNumber {
    String message() default " Is Invalid, Not is Number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] values() default {};
}
