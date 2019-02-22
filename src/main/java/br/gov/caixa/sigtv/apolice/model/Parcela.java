package br.gov.caixa.sigtv.apolice.model;

import br.gov.caixa.sigtv.arquivo.model.Arquivo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "GTVTB010_PARCELA")
@ToString(exclude = {"arquivos", "apolice"})
public class Parcela {

  @Id
  @SequenceGenerator(name = "GTVSQ010_PARCELA", sequenceName = "GTVSQ010_PARCELA")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "GTVSQ010_PARCELA")
  @Column(name = "NU_PARCELA")
  private Long id;

  @Generated(value = GenerationTime.INSERT)
  @Column(name = "CO_CODIGO")
  private Long codigo;

  @NotNull
  @Column(name = "DH_DATA_VENCIMENTO", nullable = false)
  private LocalDate dataVencimento;

  @Min(0)
  @NotNull
  @Column(name = "NU_VALOR")
  private BigDecimal valor;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "GTVTB013_PARCELA_ARQUIVO",
      joinColumns = @JoinColumn(name = "NU_PARCELA"),
      inverseJoinColumns = @JoinColumn(name = "NU_ARQUIVO")
  )
  private List<Arquivo> arquivos;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "NU_APOLICE", referencedColumnName = "NU_APOLICE")
  private Apolice apolice;

}
