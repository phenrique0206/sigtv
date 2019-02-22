package br.gov.caixa.sigtv.endereco.repository;

import br.gov.caixa.sigtv.endereco.model.Municipio;
import br.gov.caixa.sigtv.util.repository.ReadOnlyRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "municipio", path = "municipio", itemResourceRel = "municipio")
public interface IMunicipioRepository
    extends ReadOnlyRepository<Municipio, Long>,
            QueryByExampleExecutor<Municipio> {


}
