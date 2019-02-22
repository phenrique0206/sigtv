package br.gov.caixa.sigtv.base.model;

import br.gov.caixa.sigtv.empresa.model.Empresa;
import br.gov.caixa.sigtv.endereco.model.Municipio;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.core.Relation;

@Data
@Relation(collectionRelation = "base")
public class ConsultaBaseDto implements Identifiable {

  private Long id;
  private TipoBase tipo;
  private String cnpj;
  private String nome;

  @Getter(AccessLevel.NONE)
  private Municipio municipio;

  @Getter(AccessLevel.NONE)
  private Empresa empresa;

  public ConsultaBaseDto(Base base){
    BeanUtils.copyProperties(base,this);
  }

  public String getUf() {
    return this.municipio.getUf().getSigla();
  }

  public String getMunicipio() {
    return this.municipio.getNome();
  }

  public String getEmpresa() {
    return this.empresa.getNomeReduzido();
  }

}
