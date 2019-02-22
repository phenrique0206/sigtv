package br.gov.caixa.sigtv.util.validation;


import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Essa classe representa um conjunto de erros de validação. Ela
 * pode ser usada para retornar um resumo da validação para o cliente.
 */
@Getter
public class ValidationResult implements Serializable {

  List<ValidationError> errors;

  public ValidationResult() {
    this.errors =  new ArrayList<>();
  }

  public void addError(ValidationError error){
    this.errors.add(error);
  }

}