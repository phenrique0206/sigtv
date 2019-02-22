package br.gov.caixa.sigtv.apolice.repository;

import br.gov.caixa.sigtv.apolice.model.Apolice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "apolice", path = "apolice", itemResourceRel = "apolice")
public interface IApoliceRepository extends PagingAndSortingRepository<Apolice, Long> {


}
