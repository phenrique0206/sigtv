package br.gov.caixa.sigtv.apolice.model;

import br.gov.caixa.sigtv.arquivo.model.Arquivo;
import br.gov.caixa.sigtv.empresa.model.Empresa;
import br.gov.caixa.sigtv.util.validation.annotations.DataInicioDataFim;
import br.gov.caixa.sigtv.util.validation.annotations.Email;
import br.gov.caixa.sigtv.util.validation.annotations.NullOrNotBlank;
import br.gov.caixa.sigtv.util.validation.annotations.Telefone;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "GTVTB002_APOLICE")
@ToString(exclude = {"empresa", "divisoesBase", "parcelas", "arquivos"})
@DataInicioDataFim(dataInicio = "dataInicial", dataFim = "dataFinal")
public class Apolice {

  @Id
  @SequenceGenerator(name = "GTVSQ002_APOLICE", sequenceName = "GTVSQ002_APOLICE")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "GTVSQ002_APOLICE")
  @Column(name = "NU_APOLICE")
  private Long id;

  @Generated(value = GenerationTime.INSERT)
  @Column(name = "CO_CODIGO")
  private Long codigo;

  @NotNull
  @Column(name = "DH_DATA_INICIAL", nullable = false)
  private LocalDate dataInicial;

  @NotNull
  @Column(name = "DH_DATA_FINAL", nullable = false)
  private LocalDate dataFinal;

  @Enumerated(EnumType.STRING)
  @Column(name = "IC_STATUS")
  private StatusApolice status;

  @Size(min = 1, max = 255)
  @NullOrNotBlank
  @Column(name = "NO_SEGURADORA")
  private String seguradora;

  @Size(min = 1, max = 255)
  @NullOrNotBlank
  @Column(name = "NO_CORRETOR")
  private String corretor;

  @Telefone
  @Column(name = "NU_TELEFONE")
  private String telefone;

  @Size(min = 1, max = 255)
  @Email
  @Column(name = "NO_EMAIL")
  private String email;

  @Column(name = "IC_ATIVA")
  private Boolean ativa;

  @Min(0)
  @NotNull
  @Column(name = "NU_ROTINEIRO_SEM_ESCOLTA")
  private BigDecimal valorRotineiroSemEscolta;

  @Min(0)
  @NotNull
  @Column(name = "NU_PONTO_A_PONTO")
  private BigDecimal valorPontoAPonto;

  @Min(0)
  @NotNull
  @Column(name = "NU_CALCADA")
  private BigDecimal valorCalcada;

  @Min(0)
  @Column(name = "NU_ABASTECIMENTO_PAE")
  private BigDecimal valorAbastecimentoPae;

  @Min(0)
  @Column(name = "NU_AEREO")
  private BigDecimal valorAereo;

  @Min(0)
  @Column(name = "NU_FLUVIAL")
  private BigDecimal valorFluvial;

  @Min(0)
  @Column(name = "NU_PREMIO_TOTAL")
  private BigDecimal valorPremioTotal;

  @Size(min = 1, max = 255)
  @NullOrNotBlank
  @Column(name = "NO_HORARIO_PERMITIDO")
  private String horarioPermitido;

  @Column(name = "DH_DATA_PRIMEIRA_PARCELA", nullable = false)
  private LocalDate dataPrimeiraParcela;

  @Min(1)
  @Column(name = "NU_PARCELAS")
  private Integer numeroParecelas;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "NU_EMPRESA", referencedColumnName = "NU_EMPRESA")
  private Empresa empresa;

  @Valid
  @OneToMany(
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      mappedBy = "apolice",
      orphanRemoval = true
  )
  private List<DivisaoApoliceBase> divisoesBase;

  @Valid
  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "apolice",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private List<Parcela> parcelas;

  @Valid
  @OneToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "GTVTB012_APOLICE_ARQUIVO",
      joinColumns = @JoinColumn(name = "NU_APOLICE"),
      inverseJoinColumns = @JoinColumn(name = "NU_ARQUIVO")
  )
  private List<Arquivo> arquivos;

  @PrePersist @PreUpdate
  public void syncAssociations() {

    if (this.parcelas != null) {
      this.parcelas.forEach(p -> p.setApolice(this));
    }

    if (this.divisoesBase != null) {
      this.divisoesBase.forEach(d -> d.setApolice(this));
    }

  }

}
