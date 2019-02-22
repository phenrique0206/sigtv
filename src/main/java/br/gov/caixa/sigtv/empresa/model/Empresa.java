package br.gov.caixa.sigtv.empresa.model;

import br.com.caelum.stella.bean.validation.CNPJ;
import br.gov.caixa.sigtv.apolice.model.Apolice;
import br.gov.caixa.sigtv.base.model.Base;
import br.gov.caixa.sigtv.contato.model.Contato;
import br.gov.caixa.sigtv.endereco.model.Endereco;
import br.gov.caixa.sigtv.util.validation.annotations.NullOrNotBlank;
import br.gov.caixa.sigtv.util.validation.annotations.UniqueIgnoreCase;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "GTVTB001_EMPRESA")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@ToString(exclude = {"contatos", "apolices", "bases"})
@UniqueIgnoreCase(entity = Empresa.class, field = "cnpj")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property  = "id",
    scope     = Long.class
)
public class Empresa implements Identifiable {

  @Id
  @SequenceGenerator(name = "GTVSQ001_EMPRESA", sequenceName = "GTVSQ001_EMPRESA")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "GTVSQ001_EMPRESA")
  @Column(name = "NU_EMPRESA")
  private Long id;

  @Size(min = 1, max = 255)
  @NullOrNotBlank()
  @NotNull
  @Column(name = "NO_RAZAO_SOCIAL")
  private String razaoSocial;

  @Size(min = 1, max = 255)
  @NullOrNotBlank()
  @NotNull
  @Column(name = "NO_NOME_REDUZIDO")
  private String nomeReduzido;

  @NotNull
  @CNPJ
  @Column(name = "NU_CNPJ", nullable = false, unique = true)
  private String cnpj;

  @Size(min = 1, max = 255)
  @NullOrNotBlank
  @Column(name = "DE_OBSERVACAO")
  private String observacao;

  @Embedded
  @NotNull
  private Endereco endereco;

  @Enumerated(EnumType.STRING)
  @Column(name = "IC_SITUACAO")
  private SituacaoEmpresa situacao;

  @Valid
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
  private List<Apolice> apolices;

  @Valid
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
  private List<Contato> contatos;

  @Valid
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
  private List<Base> bases;

}
