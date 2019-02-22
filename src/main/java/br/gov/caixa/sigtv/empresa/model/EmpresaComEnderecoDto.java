package br.gov.caixa.sigtv.empresa.model;

import br.gov.caixa.sigtv.apolice.model.Apolice;
import br.gov.caixa.sigtv.contato.model.Contato;
import br.gov.caixa.sigtv.contato.model.ContatoComDepartamentoDto;
import br.gov.caixa.sigtv.endereco.model.Endereco;
import br.gov.caixa.sigtv.endereco.model.EnderecoCompletoDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.core.Relation;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Relation(collectionRelation = "empresa")
public class EmpresaComEnderecoDto  {

  private Long id;
  private String razaoSocial;
  private String nomeReduzido;
  private String cnpj;
  private String observacao;
  private SituacaoEmpresa situacao;
  private List<Apolice> apolices;

  @Getter(AccessLevel.NONE)
  private Endereco endereco;

  @Getter(AccessLevel.NONE)
  private List<Contato> contatos;

  public EmpresaComEnderecoDto(Empresa empresa){
    BeanUtils.copyProperties(empresa,this);
  }

  public EnderecoCompletoDto getEndereco() {
    return new EnderecoCompletoDto(this.endereco);
  }

  public List<ContatoComDepartamentoDto> getContatos() {
    return this.contatos
        .stream()
        .map(ContatoComDepartamentoDto::new)
        .collect(Collectors.toList());
  }

}
