package br.gov.caixa.sigtv.util.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Objects;

/**
 * Essa classe representa um erro validação específico para um
 * atributo de uma classe. Ela segue o mesmo formato utilizado
 * por padrão pelo o Spring Data REST.
 */
@AllArgsConstructor
@Builder
@Getter
public class ValidationError {

  private String entity;
  private String property;
  private String invalidValue;
  private String message;

  /**
   * Constrói um ValidationError a partir de um FieldError, classe do Spring que
   * representa um erro de validação em um atributo. Um FieldError tipicamente é
   * obitido através de um Validator.
   *
   * @param springError - instância de FieldError
   */
  public ValidationError(ObjectError springError) {
    this.entity = springError.getObjectName();
    this.message = springError.getDefaultMessage();

    if (springError instanceof FieldError) {

      FieldError fieldError = (FieldError) springError;

      this.property = fieldError.getField();
      this.invalidValue = Objects.requireNonNull(fieldError.getRejectedValue()).toString();

    }


  }

}
