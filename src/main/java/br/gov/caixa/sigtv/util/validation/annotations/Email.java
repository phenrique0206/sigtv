package br.gov.caixa.sigtv.util.validation.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Pattern(regexp = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
public @interface Email {
  String message() default "{br.gov.caixa.sigtv.util.validation.annotations.Email.message}";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };
}
