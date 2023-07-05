package com.msb.epay.customValidator.handler;

import com.msb.epay.customValidator.IsAlpha;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class IsAlphaHandler implements ConstraintValidator<IsAlpha, String> {
  private String message;
  private List<String> allowable;
  private boolean isRequired;

  @Override
  public void initialize(IsAlpha constraintAnnotation) {
    this.message = constraintAnnotation.message();
    this.allowable = Arrays.asList(constraintAnnotation.values());
    this.isRequired = constraintAnnotation.isRequired();
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (isRequired) {
      return StringUtils.isNotBlank(s) && doValid(s, constraintValidatorContext);
    }
    return StringUtils.isEmpty(s) || doValid(s, constraintValidatorContext);
  }

  private boolean doValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    Boolean valid = s.matches("^[a-zA-Z]+");
    if (!Boolean.TRUE.equals(valid)) {
      constraintValidatorContext.disableDefaultConstraintViolation();
      constraintValidatorContext
          .buildConstraintViolationWithTemplate(this.allowable.toString().concat(message))
          .addConstraintViolation();
    }

    return valid;
  }
}
