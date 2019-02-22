package br.gov.caixa.sigtv.endereco.repository;

import br.gov.caixa.sigtv.endereco.model.Uf;
import br.gov.caixa.sigtv.util.repository.ReadOnlyRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "uf", path = "uf", itemResourceRel = "uf")
public interface IUfRepository
    extends ReadOnlyRepository<Uf, Long>,
            QueryByExampleExecutor<Uf> {


}
