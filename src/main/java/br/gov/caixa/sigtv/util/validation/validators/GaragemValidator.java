package br.gov.caixa.sigtv.util.validation.validators;

import br.gov.caixa.sigtv.base.model.Base;
import br.gov.caixa.sigtv.base.model.TipoBase;
import br.gov.caixa.sigtv.util.validation.annotations.Garagem;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class GaragemValidator implements ConstraintValidator<Garagem, Base>{

  private final Logger logger;

  @Override
  public void initialize(Garagem constraintAnnotation) {
    this.logger.debug("[SIGTV] Inicializando validator: {}", constraintAnnotation);
  }

  @Override
  public boolean isValid(Base base, ConstraintValidatorContext context) {

    this.logger.debug("[SIGTV] Validando objeto: {}", base);

    TipoBase tipo = base.getTipo();
    Base baseCustodia = base.getCustodia();

    boolean valid = !TipoBase.GARAGEM.equals(tipo) ||
          (
           baseCustodia != null &&
          !baseCustodia.getId().equals(base.getId()) &&
           TipoBase.COFRE_FORTE.equals(baseCustodia.getTipo())
          );

    if (!valid) {
      context.disableDefaultConstraintViolation();

      context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
             .addPropertyNode("tipo")
             .addConstraintViolation();
    }

    return valid;

  }
}
