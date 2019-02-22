package br.gov.caixa.sigtv.endereco.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@Table(name = "GTVTB005_ESTADO")
@ToString
public class Uf {

  @Id
  @SequenceGenerator(name = "GTVSQ006_ESTADO", sequenceName = "GTVSQ006_ESTADO")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "GTVSQ006_ESTADO")
  @Column(name = "NU_ESTADO")
  private Long id;

  @Pattern(regexp = "[A-Z]{2}")
  @NotNull
  @Column(name = "SG_NOME")
  private String sigla;

}
