package br.gov.caixa.sigtv.contato.model;

import br.gov.caixa.sigtv.base.model.Base;
import br.gov.caixa.sigtv.departamento.model.Departamento;
import br.gov.caixa.sigtv.empresa.model.Empresa;
import br.gov.caixa.sigtv.util.validation.annotations.NullOrNotBlank;
import br.gov.caixa.sigtv.util.validation.annotations.Telefone;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "GTVTB003_CONTATO")
@ToString(exclude = {"empresa", "departamento", "base"})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property  = "id",
    scope     = Long.class
)
public class Contato {

  @Id
  @SequenceGenerator(name = "GTVSQ004_CONTATO", sequenceName = "GTVSQ004_CONTATO")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "GTVSQ004_CONTATO")
  @Column(name = "NU_CONTATO")
  private Long id;

  @Size(min = 1, max = 255)
  @NullOrNotBlank()
  @NotNull
  @Column(name = "NO_NOME")
  private String nome;

  @Telefone
  @Column(name = "NU_TELEFONE")
  private String telefone;

  @ElementCollection
  @CollectionTable(
      name = "GTVTB008_CONTATO_EMAIL",
      joinColumns = @JoinColumn(name = "NU_CONTATO")
  )
  @Column(name = "NO_EMAIL")
  private List<String> emails;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "NU_DEPARTAMENTO", referencedColumnName = "NU_DEPARTAMENTO")
  private Departamento departamento;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "NU_EMPRESA", referencedColumnName = "NU_EMPRESA")
  private Empresa empresa;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "NU_BASE", referencedColumnName = "NU_BASE")
  private Base base;

}
