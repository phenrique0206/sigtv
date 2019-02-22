package br.gov.caixa.sigtv.endereco.controller;

import br.gov.caixa.sigtv.endereco.model.Uf;
import br.gov.caixa.sigtv.endereco.repository.IUfRepository;
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
public class UfController {

  private final IUfRepository ufRepository;

  @GetMapping("/uf/filter")
  public @ResponseBody ResponseEntity<?> filtro(
    Uf uf,
    Pageable page,
    PagedResourcesAssembler<Uf> assembler
  ){

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    Example example = Example.of(uf, matcher);

    Page<Uf> ufs = this.ufRepository.findAll(example, page);

    return ResponseEntity.ok(assembler.toResource(ufs));

  }

}
