package br.gov.caixa.sigtv.util.validation.annotations;

import br.gov.caixa.sigtv.util.validation.validators.SafeDeleteValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SafeDeleteValidator.class)
public @interface SafeDelete {
  String message() default "{br.gov.caixa.sigtv.util.validation.annotations.SafeDelete.message}";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };
}
