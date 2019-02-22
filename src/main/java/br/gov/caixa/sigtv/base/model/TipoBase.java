package br.gov.caixa.sigtv.base.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoBase {

  COFRE_FORTE("Cofre Forte"),
  GARAGEM("Garagem");

  private String nome;

}
