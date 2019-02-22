package br.gov.caixa.sigtv.util.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID> extends Repository<T, ID> {

  Optional<T> findById(ID var1);

  boolean existsById(ID var1);

  Iterable<T> findAll();

  Iterable<T> findAllById(Iterable<ID> var1);

  long count();

  Iterable<T> findAll(Sort var1);

  Page<T> findAll(Pageable var1);

}
