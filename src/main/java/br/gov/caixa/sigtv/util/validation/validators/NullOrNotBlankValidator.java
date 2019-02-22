package br.gov.caixa.sigtv.util.validation.validators;

import br.gov.caixa.sigtv.util.validation.annotations.NullOrNotBlank;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {

   private final Logger logger;

   @Override
   public void initialize(NullOrNotBlank constraint) {
      this.logger.debug("[SIGTV] Inicializando validator: {}", constraint);
   }

   @Override
   public boolean isValid(String str, ConstraintValidatorContext context) {
      this.logger.debug("[SIGTV] Validando objeto: {}", str);
      return str == null || str.trim().length() > 0;

   }
}
