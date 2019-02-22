package br.gov.caixa.sigtv.apolice.repository;

import br.gov.caixa.sigtv.apolice.model.Parcela;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "parcela", path = "parcela", itemResourceRel = "parcela")
public interface IParcelaRepository extends PagingAndSortingRepository<Parcela, Long> {


}
