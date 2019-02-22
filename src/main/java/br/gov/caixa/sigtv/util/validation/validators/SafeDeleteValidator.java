package br.gov.caixa.sigtv.util.validation.validators;

import br.gov.caixa.sigtv.util.validation.annotations.CheckForDelete;
import br.gov.caixa.sigtv.util.validation.annotations.SafeDelete;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Implementa a validação definida na interface SafeDelete. Esse validator
 * procura os métodos da entidade a ser validada anotados com CheckForDelete
 * e os executam, o resultado da validação é um AND do resultado de todos os métodos.
 */
@RequiredArgsConstructor
public class SafeDeleteValidator implements ConstraintValidator<SafeDelete, Object> {

  private final Logger logger;

  @Override
  public void initialize(SafeDelete constraintAnnotation) {
    this.logger.debug("[SIGTV] Inicializando validator: {}", constraintAnnotation);
  }

  @Override
  public boolean isValid(Object object, ConstraintValidatorContext context) {

    this.logger.debug("[SIGTV] Validando objeto: {}", object);

    Method[] methods = object.getClass().getMethods();

    return Arrays.stream(methods)
          .filter(m -> m.getAnnotation(CheckForDelete.class) != null)
          .map(m -> (Boolean) invokeMethod(m, object))
          .reduce(true, (a, b) -> a && b);
  }

  /**
   * Esse é um método auxiliar para facilitar o tratamento de exceção dentro
   * da expressão lambda. Sua função é simplesmente capturar as exceções checadas
   * e relançá-las como RuntimeException. Os parâmetros seguem a mesma estrutura
   * do método invoke da classe Method, precedidos da referência do método em sim.
   *
   */
  private Object invokeMethod(Method method, Object obj, Object... args) {
    try {
      return method.invoke(obj, args);
    } catch (IllegalAccessException | InvocationTargetException e) {
      this.logger.error("[SIGTV] {}", e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }

}
