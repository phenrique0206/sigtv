package br.gov.caixa.sigtv.empresa.model;

import br.gov.caixa.sigtv.apolice.model.Apolice;
import br.gov.caixa.sigtv.contato.model.ContatoComDepartamentoProjection;
import br.gov.caixa.sigtv.endereco.model.EnderecoCompletoProjection;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "empresaComEndereco", types = { Empresa.class })
public interface EmpresaComEnderecoProjection {

  Long getId();
  String getRazaoSocial();
  String getNomeReduzido();
  String getCnpj();
  String getObservacao();
  EnderecoCompletoProjection getEndereco();
  SituacaoEmpresa getSituacao();
  List<Apolice> getApolices();
  List<ContatoComDepartamentoProjection> getContatos();

}
