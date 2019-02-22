package br.gov.caixa.sigtv.base.model;

import br.com.caelum.stella.bean.validation.CNPJ;
import br.gov.caixa.sigtv.contato.model.Contato;
import br.gov.caixa.sigtv.empresa.model.Empresa;
import br.gov.caixa.sigtv.endereco.model.Municipio;
import br.gov.caixa.sigtv.util.validation.annotations.Garagem;
import br.gov.caixa.sigtv.util.validation.annotations.NullOrNotBlank;
import br.gov.caixa.sigtv.util.validation.annotations.UniqueIgnoreCase;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "GTVTB007_BASE")
@ToString(exclude = {"contatos", "custodia"})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Garagem
@UniqueIgnoreCase(entity = Base.class, field = "cnpj")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property  = "id",
    scope     = Long.class
)
public class Base implements Identifiable {

  @Id
  @SequenceGenerator(name = "GTVSQ007_BASE", sequenceName = "GTVSQ007_BASE")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "GTVSQ007_BASE")
  @Column(name = "NU_BASE")
  private Long id;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "IC_TIPO")
  private TipoBase tipo;

  @Size(min = 1, max = 255)
  @NullOrNotBlank()
  @NotNull
  @Column(name = "NO_NOME")
  private String nome;

  @NotNull
  @CNPJ
  @Column(name = "NU_CNPJ", nullable = false, unique = true)
  private String cnpj;

  @Size(min = 1, max = 255)
  @NullOrNotBlank
  @Column(name = "DE_OBSERVACAO")
  private String observacao;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "IC_AUTORIZACAO_PF")
  private AutorizacaoPF autorizacaoPF;

  @Size(min = 1, max = 255)
  @NullOrNotBlank
  @NotNull
  @Column(name = "ED_ENDERECO", nullable = false)
  private String endereco;

  @Size(min = 1, max = 255)
  @NullOrNotBlank
  @NotNull
  @Column(name = "ED_BAIRRO", nullable = false)
  private String bairro;

  @Min(1)
  @NotNull
  @Column(name = "ED_NUMERO", nullable = false)
  private Integer numero;

  @Size(min = 1, max = 255)
  @NullOrNotBlank
  @Column(name = "ED_COMPLEMENTO")
  private String complemento;

  @NotNull
  @Pattern(regexp = "[0-9]{8}")
  @Column(name = "ED_CEP")
  private String cep;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "NU_MUNICIPIO", referencedColumnName = "NU_MUNICIPIO")
  private Municipio municipio;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "NU_EMPRESA", referencedColumnName = "NU_EMPRESA")
  private Empresa empresa;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "NU_BASE_GARAGEM", referencedColumnName = "NU_BASE")
  private Base custodia;

  @Valid
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "base")
  private List<Contato> contatos;

}
