package br.gov.caixa.sigtv.departamento.repository;

import br.gov.caixa.sigtv.departamento.model.Departamento;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "departamento", path = "departamento", itemResourceRel = "departamento")
public interface IDepartamentoRepository extends PagingAndSortingRepository<Departamento, Long> {

  Optional<Departamento> findByNomeIgnoreCase(@NotNull String nome);

}
