package br.gov.caixa.sigtv.apolice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusApolice {

  REGULAR("Regular"),
  VENCIDA("Vencida"),
  A_VENCER("A vencer");

  private String nome;

}
