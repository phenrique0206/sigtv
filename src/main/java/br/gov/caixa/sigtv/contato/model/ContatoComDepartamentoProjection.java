package br.gov.caixa.sigtv.contato.model;

import br.gov.caixa.sigtv.departamento.model.Departamento;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "contatoComDepartamento", types = Contato.class)
public interface ContatoComDepartamentoProjection {

  Long getId();
  String getNome();
  String getTelefone();
  List<String> getEmails();
  Departamento getDepartamento();

}
