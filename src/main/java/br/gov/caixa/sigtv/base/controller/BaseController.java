package br.gov.caixa.sigtv.base.controller;

import br.gov.caixa.sigtv.base.model.Base;
import br.gov.caixa.sigtv.base.model.ConsultaBaseDto;
import br.gov.caixa.sigtv.base.repository.IBaseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@RepositoryRestController
public class BaseController {

  private final IBaseRepository baseRepository;
  private final Logger logger;

  @GetMapping("/base/filter")
  public @ResponseBody ResponseEntity<?> filtro(
      Base base,
      Pageable page,
      PagedResourcesAssembler<ConsultaBaseDto> assembler
  ){

    this.logger.debug("[SIGTV] Executando filtro de base {}", base);

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    Example<Base> example = Example.of(base, matcher);

    Page<Base> bases = this.baseRepository.findAll(example, page);

    Page<ConsultaBaseDto> baseResources = bases.map(ConsultaBaseDto::new);

    return ResponseEntity.ok(assembler.toResource(baseResources));

  }

}
