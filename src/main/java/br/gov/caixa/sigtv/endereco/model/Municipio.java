package br.gov.caixa.sigtv.endereco.model;

import br.gov.caixa.sigtv.util.validation.annotations.NullOrNotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "GTVTB006_MUNICIPIO")
@ToString(exclude = "uf")
public class Municipio implements Identifiable {

  @Id
  @SequenceGenerator(name = "GTVSQ007_MUNICIPIO", sequenceName = "GTVSQ007_MUNICIPIO")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "GTVSQ007_MUNICIPIO")
  @Column(name = "NU_MUNICIPIO")
  private Long id;

  @Size(min = 3, max = 255)
  @NullOrNotBlank
  @NotBlank
  @Column(name = "CO_CODIGO")
  private String codigo;

  @Size(min = 3, max = 255)
  @NullOrNotBlank
  @NotBlank
  @Column(name = "NO_NOME")
  private String nome;

  @ManyToOne
  @JoinColumn(name = "NU_ESTADO", referencedColumnName = "NU_ESTADO")
  private Uf uf;

}
