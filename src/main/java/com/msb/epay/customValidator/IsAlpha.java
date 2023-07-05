package com.msb.epay.customValidator;


import com.msb.epay.customValidator.handler.IsAlphaHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/*
 * vadidate A-z a-z 0-0
 */
@Documented
@Constraint(validatedBy = IsAlphaHandler.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsAlpha {
  boolean isRequired() default true;

  String message() default " Is Invalid A-z a-z";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String[] values() default {};
}
