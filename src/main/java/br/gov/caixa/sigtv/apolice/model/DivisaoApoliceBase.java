package br.gov.caixa.sigtv.apolice.model;

import br.gov.caixa.sigtv.base.model.Base;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "GTVTB009_DIVISAO_APOLICE_BASE")
public class DivisaoApoliceBase {

  @Id
  @SequenceGenerator(name = "GTVSQ009_DIVISAO_APOLICE_BASE", sequenceName = "GTVSQ009_DIVISAO_APOLICE_BASE")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "GTVSQ009_DIVISAO_APOLICE_BASE")
  @Column(name = "NU_DIVISAO")
  private Long id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "NU_BASE", referencedColumnName = "NU_BASE")
  private Base base;

  @Min(0)
  @NotNull
  @Column(name = "NU_COFRE_FORTE")
  private BigDecimal valorCofreForte;

  @Min(0)
  @NotNull
  @Column(name = "NU_TESOURARIA")
  private BigDecimal valorTesouraria;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "NU_APOLICE", referencedColumnName = "NU_APOLICE")
  private Apolice apolice;

}
