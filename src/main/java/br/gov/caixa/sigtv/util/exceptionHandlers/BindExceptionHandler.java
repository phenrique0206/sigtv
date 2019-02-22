package br.gov.caixa.sigtv.util.exceptionHandlers;

import br.gov.caixa.sigtv.util.validation.ValidationError;
import br.gov.caixa.sigtv.util.validation.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Trata as exceções do tipo BindException. Essa execeção tipicamente
 * representa um error de validação, por isso é retornado 400 - Bad Request
 * como resposta, junto com o detalhamento da validação no corpo.
 */
@RequiredArgsConstructor
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class BindExceptionHandler {

  private final Logger logger;

  @ExceptionHandler(BindException.class)
  public ResponseEntity<?> handle(BindException ex) {

    this.logger.info("[SIGTV] BindException: {}", ex.getMessage());
    this.logger.debug("[SIGTV] {}", ex);

    ValidationResult validationResult = new ValidationResult();
    ex.getAllErrors().forEach(e -> validationResult.addError(new ValidationError(e)));
    return ResponseEntity.badRequest().body(validationResult);

  }

}
