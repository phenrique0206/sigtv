package br.gov.caixa.sigtv.contato.model;

import br.gov.caixa.sigtv.departamento.model.Departamento;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.core.Relation;

import java.util.List;

@Data
@Relation(collectionRelation = "contato")
public class ContatoComDepartamentoDto {

  private Long id;
  private String nome;
  private String telefone;
  private List<String> emails;
  private Departamento departamento;

  public ContatoComDepartamentoDto(Contato contato){
    BeanUtils.copyProperties(contato,this);
  }

}
