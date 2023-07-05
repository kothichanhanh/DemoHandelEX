package com.msb.epay.customValidator;


import com.msb.epay.customValidator.handler.AlphaNumericWithoutSpecialAndSpaceHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/*
 * vadidate khong nhan ky tu dac biet chuoi co dau cach
 */
@Documented
@Constraint(validatedBy = AlphaNumericWithoutSpecialAndSpaceHandler.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AlphaNumericWithoutSpecialAndSpace {
  boolean isRequired() default true;
  String message() default " Is Invalid A-z a-z 0-9 space";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
  String[] values() default {};
}
