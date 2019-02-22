package br.gov.caixa.sigtv.util.validation.annotations;

import br.gov.caixa.sigtv.util.validation.validators.GaragemValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {GaragemValidator.class})
public @interface Garagem {
  String message() default "{br.gov.caixa.sigtv.util.validation.annotations.Garagem.message}";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };
}
