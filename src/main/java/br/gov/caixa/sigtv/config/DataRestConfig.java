package br.gov.caixa.sigtv.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.validation.Validator;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class DataRestConfig extends RepositoryRestConfigurerAdapter {

  @NotNull
  private Validator validator;

  @NotNull
  private EntityManagerFactory entityManagerFactory;

  @Override
  public void configureValidatingRepositoryEventListener(
      ValidatingRepositoryEventListener validatingListener
  ) {
    validatingListener.addValidator("beforeCreate", validator);
    validatingListener.addValidator("beforeSave", validator);
  }

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

    List<Class<?>> entityClasses = getAllManagedEntityTypes(entityManagerFactory);

    // Expose id's for all entity classes
    for (Class<?> entityClass : entityClasses) {
      config.exposeIdsFor(entityClass);
    }

  }

  private List<Class<?>> getAllManagedEntityTypes(EntityManagerFactory entityManagerFactory) {
    List<Class<?>> entityClasses = new ArrayList<>();
    Metamodel metamodel = entityManagerFactory.getMetamodel();

    for (ManagedType<?> managedType : metamodel.getManagedTypes()) {
      if (managedType.getJavaType().isAnnotationPresent(Entity.class)) {
        entityClasses.add(managedType.getJavaType());
      }
    }

    return entityClasses;

  }

}