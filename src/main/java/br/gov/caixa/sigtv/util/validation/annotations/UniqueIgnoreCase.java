package br.gov.caixa.sigtv.util.validation.annotations;

import br.gov.caixa.sigtv.util.validation.validators.UniqueIgnoreCaseValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Valida se o valor do atributo de uma entidad é único no banco de dados,
 * desconsiderando maiúsculas ou minúsculas.
 *
 * @param entity Class<?> a classe de entidade usada na validação
 * @param field String o nome do atributo usado na validação
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UniqueIgnoreCaseValidator.class})
public @interface UniqueIgnoreCase {
  String message() default "{br.gov.caixa.sigtv.util.validation.annotations.UniqueIgnoreCase.message}";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };

  Class<?> entity();
  String field();
}
