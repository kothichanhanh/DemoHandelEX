package com.msb.epay.customValidator.handler;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.msb.epay.customValidator.IsAlphaNumeric;
import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;
import java.util.List;

public class IsAlphaNumericHandler implements ConstraintValidator<IsAlphaNumeric, String> {
  private String message;
  private List<String> allowable;
  private boolean isRequired;

  @Override
  public void initialize(IsAlphaNumeric constraintAnnotation) {
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
    boolean valid = s.matches("^[a-zA-Z0-9]+");
    if (!valid) {
      constraintValidatorContext.disableDefaultConstraintViolation();
      constraintValidatorContext
          .buildConstraintViolationWithTemplate(allowable.toString().concat(message))
          .addConstraintViolation();
    }

    return valid;
  }
}
