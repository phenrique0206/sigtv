package br.gov.caixa.sigtv.endereco.model;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "enderecoCompleto", types = { Endereco.class })
public interface EnderecoCompletoProjection {

  String getEndereco();
  String getBairro();
  String getComplemento();
  String getCep();
  Integer getNumero();

  @Value("#{target.getMunicipio() != null ? target.getMunicipio().getNome() : null}")
  String getMunicipio();

  @Value("#{target.getMunicipio() != null ? target.getMunicipio().getId() : null}")
  Long getIdMunicipio();

  @Value("#{target.getMunicipio() != null ? target.getMunicipio().getUf().getSigla() : null}")
  String getUf();

  @Value("#{target.getMunicipio() != null ? target.getMunicipio().getUf().getId() : null}")
  Long getIdUf();

}
