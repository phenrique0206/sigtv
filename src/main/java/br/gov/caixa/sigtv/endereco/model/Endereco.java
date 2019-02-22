package br.gov.caixa.sigtv.endereco.model;

import br.gov.caixa.sigtv.util.validation.annotations.NullOrNotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;

@Embeddable
@Getter
@Setter
@ToString(exclude = "municipio")
public class Endereco {

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

}
