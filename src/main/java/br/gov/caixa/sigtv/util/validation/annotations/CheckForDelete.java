package br.gov.caixa.sigtv.util.validation.annotations;

import java.lang.annotation.*;

/**
 * Marca um método que deve ser usado na validação de SafeDelete. O método deve não
 * receber nenhum parâmetro e retornar um Boolean.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckForDelete {
}
