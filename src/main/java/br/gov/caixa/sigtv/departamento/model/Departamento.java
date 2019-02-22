package br.gov.caixa.sigtv.departamento.model;

import br.gov.caixa.sigtv.contato.model.Contato;
import br.gov.caixa.sigtv.util.validation.annotations.*;
import br.gov.caixa.sigtv.util.validation.groups.PreDeleteValidation;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "GTVTB004_DEPARTAMENTO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@UniqueIgnoreCase(entity = Departamento.class, field = "nome")
@ToString(exclude = "contatos")
@SafeDelete(groups = PreDeleteValidation.class, message = "{br.gov.caixa.sigtv.departamento.model.Departamento.foreign_key}")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property  = "id",
    scope     = Long.class
)
public class Departamento {

  @Id
  @SequenceGenerator(name = "GTVSQ005_DEPARTAMENTO", sequenceName = "GTVSQ005_DEPARTAMENTO")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "GTVSQ005_DEPARTAMENTO")
  @Column(name = "NU_DEPARTAMENTO")
  private Long id;

  @Size(min = 1, max = 255)
  @NullOrNotBlank()
  @Column(name = "NO_NOME")
  private String nome;

  @NotNull
  @Column(name = "IC_FIXO")
  @AssertFalse(groups = PreDeleteValidation.class, message = "{br.gov.caixa.sigtv.departamento.model.Departamento.fixo}")
  private Boolean fixo;

  @Valid
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "departamento")
  private List<Contato> contatos;

  @CheckForDelete
  public Boolean checkForDelete() {
    return this.contatos.isEmpty();
  }
}
