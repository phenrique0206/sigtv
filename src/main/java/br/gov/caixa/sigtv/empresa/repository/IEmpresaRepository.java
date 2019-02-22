package br.gov.caixa.sigtv.empresa.repository;

import br.gov.caixa.sigtv.empresa.model.Empresa;
import br.gov.caixa.sigtv.empresa.model.EmpresaComEnderecoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RepositoryRestResource(
    collectionResourceRel = "empresa",
    path = "empresa",
    itemResourceRel = "empresa",
    excerptProjection = EmpresaComEnderecoProjection.class
)
public interface IEmpresaRepository
    extends PagingAndSortingRepository<Empresa, Long>,
            QueryByExampleExecutor<Empresa> {

  boolean existsByCnpj(@NotNull String cnpj);
  Optional<Empresa> findByCnpjIgnoreCase(@NotNull String cnpj);

  @RestResource(path = "empresaPorUf", rel = "empresaPorUf")
  Page<Empresa> findAllByEnderecoMunicipioUfSiglaIgnoreCase(@NotNull String sigla, Pageable page);

  @RestResource(path = "empresaPorMunicipio", rel = "empresaPorMunicipio")
  Page<Empresa> findAllByEnderecoMunicipioNomeContainingIgnoreCase(@NotNull String nome, Pageable page);
}
