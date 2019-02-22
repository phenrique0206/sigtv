package br.gov.caixa.sigtv.base.repository;

import br.gov.caixa.sigtv.base.model.Base;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RepositoryRestResource(
    collectionResourceRel = "base",
    path = "base",
    itemResourceRel = "base"
)
public interface IBaseRepository
    extends PagingAndSortingRepository<Base, Long>,
            QueryByExampleExecutor<Base>
{
    Optional<Base> findByCnpjIgnoreCase(@NotNull String cnpj);

}
