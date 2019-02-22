package br.gov.caixa.sigtv.util.validation.annotations;

import br.gov.caixa.sigtv.util.validation.validators.NullOrNotBlankValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NullOrNotBlankValidator.class)
public @interface NullOrNotBlank {
  String message() default "{br.gov.caixa.sigtv.util.validation.annotations.NullOrNotBlank.message}";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };
}
