package br.gov.caixa.sigtv.util.validation.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Pattern(regexp = "[0-9]{9,15}")
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
public @interface Telefone {
  String message() default "{br.gov.caixa.sigtv.util.validation.annotations.Telefone.message}";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };
}
