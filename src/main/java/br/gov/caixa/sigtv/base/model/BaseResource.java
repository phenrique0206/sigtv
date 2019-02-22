package br.gov.caixa.sigtv.base.model;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.core.Relation;

@Relation(value = "base", collectionRelation = "bases")
public class BaseResource extends Resource<ConsultaBaseDto> {

  public BaseResource(ConsultaBaseDto content, Link... links) {
    super(content, links);
  }
}
