package br.gov.caixa.sigtv.empresa.controller;

import br.gov.caixa.sigtv.empresa.model.Empresa;
import br.gov.caixa.sigtv.empresa.model.EmpresaComEnderecoDto;
import br.gov.caixa.sigtv.empresa.repository.IEmpresaRepository;
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
public class EmpresaController {

  private final IEmpresaRepository empresaRepository;
  private final Logger logger;

  @GetMapping("/empresa/filter")
  public @ResponseBody ResponseEntity<PagedResources<Resource<EmpresaComEnderecoDto>>> filtro(
    Empresa empresa,
    Pageable page,
    PagedResourcesAssembler<EmpresaComEnderecoDto> assembler
  ){

    this.logger.debug("[SIGTV] Executando filtro de empresa {}", empresa);

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    Example example = Example.of(empresa, matcher);

    Page<Empresa> empresas = this.empresaRepository.findAll(example, page);
    Page<EmpresaComEnderecoDto> empresasDto = empresas.map(EmpresaComEnderecoDto::new);

    return ResponseEntity.ok(assembler.toResource(empresasDto));

  }

}
