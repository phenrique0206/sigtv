package br.gov.caixa.sigtv.arquivo.repository;

import br.gov.caixa.sigtv.arquivo.model.Arquivo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(
    collectionResourceRel = "arquivo",
    path = "arquivo",
    itemResourceRel = "arquivo"
)
public interface IArquivoRepository extends PagingAndSortingRepository<Arquivo, Long> {

  Optional<Arquivo> findOneByUuid(String uuid);
  void deleteByUuid(String uuid);

}
