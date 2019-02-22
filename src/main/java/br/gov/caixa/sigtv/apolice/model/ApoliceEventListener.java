package br.gov.caixa.sigtv.apolice.model;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
@RequiredArgsConstructor
public class ApoliceEventListener {

  private final Logger logger;

  @HandleBeforeSave
  public void beforeSave(Apolice apolice) {

    this.logger.debug("[SIGTV] BEFORE SAVE: {}", apolice);
    apolice.syncAssociations();

  }

}
