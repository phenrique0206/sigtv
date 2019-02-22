package br.gov.caixa.sigtv.util.validation.validators;

import br.gov.caixa.sigtv.util.validation.annotations.DataInicioDataFim;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
public class DataInicioDataFimValidator
    implements ConstraintValidator<DataInicioDataFim, Object> {

  private final Logger logger;
  private String dataInicioAttribute;
  private String dataFimAttribute;

  @Override
  public void initialize(DataInicioDataFim constraintAnnotation) {
    this.logger.debug("[SIGTV] Inicializando validator: {}", constraintAnnotation);
    this.dataInicioAttribute = constraintAnnotation.dataInicio();
    this.dataFimAttribute = constraintAnnotation.dataFim();
  }

  @Override
  public boolean isValid(
      Object object,
      ConstraintValidatorContext constraintValidatorContext
  ) {

    this.logger.debug("[SIGTV] Validando objeto: {}", object);

    try {

      String dataInicioStr = BeanUtils.getProperty(object, this.dataInicioAttribute);
      String dataFimStr = BeanUtils.getProperty(object, this.dataFimAttribute);

      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

      Date dataInicio = formatter.parse(dataInicioStr);
      Date dataFim = formatter.parse(dataFimStr);

      return dataInicio.before(dataFim);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
