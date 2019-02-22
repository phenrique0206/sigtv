package br.gov.caixa.sigtv.base.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AutorizacaoPF {

  REGULAR("Regular"),
  VENCIDA("Vencida"),
  A_VENCER("A vencer");

  private String nome;

}