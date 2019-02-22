package br.gov.caixa.sigtv.util.eventHandlers;

import br.gov.caixa.sigtv.util.validation.groups.PreDeleteValidation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validator;

/**
 * Esse EventHandler executa a validação JSR 303 ao deletar uma entidade,
 * utilizando o grupo PreDeleteValidation.
 */
@Component
@RepositoryEventHandler
@RequiredArgsConstructor
public class BeforeDeleteEventHandler {

  private final Validator validator;
  private final Logger logger;

  @HandleBeforeDelete
  public void beforeDelete(Object entity) throws BindException {

    this.logger.info("[SIGTV] Validando entidade: {}", entity);

    SpringValidatorAdapter springValidator = new SpringValidatorAdapter(this.validator);
    BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(
        entity, entity.getClass().getName());

    springValidator.validate(entity, bindingResult, PreDeleteValidation.class);

    if (bindingResult.hasErrors()) {
      this.logger.debug("[SIGTV] Erros de validação: {}", bindingResult.getAllErrors());
      throw new BindException(bindingResult);
    }

  }

}
