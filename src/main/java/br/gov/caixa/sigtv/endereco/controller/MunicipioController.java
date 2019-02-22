package br.gov.caixa.sigtv.endereco.controller;

import br.gov.caixa.sigtv.endereco.model.Municipio;
import br.gov.caixa.sigtv.endereco.repository.IMunicipioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@RepositoryRestController
public class MunicipioController {

  private final IMunicipioRepository municipioRepository;

  @GetMapping("/municipio/filter")
  public @ResponseBody ResponseEntity<?> filtro(
    Municipio municipio,
    Pageable page,
    PagedResourcesAssembler<Municipio> assembler
  ){

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    Example example = Example.of(municipio, matcher);

    Page<Municipio> municipios = this.municipioRepository.findAll(example, page);

    return ResponseEntity.ok(assembler.toResource(municipios));

  }

}
