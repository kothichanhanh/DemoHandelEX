package com.msb.epay.customValidator;


import com.msb.epay.customValidator.handler.IsAlphaNumericHandler;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;
/*
* vadidate A-z a-z 0-0
* */
@Documented
@Constraint(validatedBy = IsAlphaNumericHandler.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsAlphaNumeric {
  boolean isRequired() default true;

  String message() default " IS Invalid A-z a-z 0-9";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String[] values() default {};
}