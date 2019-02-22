package br.gov.caixa.sigtv.contato.repository;

import br.gov.caixa.sigtv.contato.model.Contato;
import br.gov.caixa.sigtv.contato.model.ContatoComDepartamentoProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "contato",
    path = "contato",
    itemResourceRel = "contato",
    excerptProjection = ContatoComDepartamentoProjection.class
)
public interface IContatoRepository extends PagingAndSortingRepository<Contato, Long> {


}
