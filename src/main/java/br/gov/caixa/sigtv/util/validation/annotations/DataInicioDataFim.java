package br.gov.caixa.sigtv.util.validation.annotations;

import br.gov.caixa.sigtv.util.validation.validators.DataInicioDataFimValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {DataInicioDataFimValidator.class})
public @interface DataInicioDataFim {
  String message() default "{br.gov.caixa.sigtv.util.validation.annotations.DataInicioDataFim.message}";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };
  String dataInicio();
  String dataFim();
}
