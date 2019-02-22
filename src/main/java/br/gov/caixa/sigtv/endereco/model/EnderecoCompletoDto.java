package br.gov.caixa.sigtv.endereco.model;


import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.core.Relation;

@Data
@Relation(collectionRelation = "endereco")
public class EnderecoCompletoDto {

  private String endereco;
  private String bairro;
  private Integer numero;
  private String complemento;
  private String cep;

  @Getter(AccessLevel.NONE)
  private Municipio municipio;

  public EnderecoCompletoDto(Endereco endereco) {
    BeanUtils.copyProperties(endereco, this);
  }

  public String getMunicipio() {
    return this.municipio != null ? this.municipio.getNome() : null;
  }

  public Long getIdMunicipio() {
    return this.municipio != null ? this.municipio.getId() : null;
  }

  public String getUf() {
    return this.municipio != null ? this.municipio.getUf().getSigla() : null;
  }

  public Long getIdUf() {
    return this.municipio != null ? this.municipio.getUf().getId() : null;
  }


}
