package br.gov.caixa.sigtv.empresa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SituacaoEmpresa {

  REGULAR("Regular"),
  ENCERRADA("Encerrada"),
  IMPEDIDA("Impedida");

  private String nome;

}
