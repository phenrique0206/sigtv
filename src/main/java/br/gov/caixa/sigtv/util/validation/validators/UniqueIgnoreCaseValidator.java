package br.gov.caixa.sigtv.util.validation.validators;

import br.gov.caixa.sigtv.util.validation.annotations.UniqueIgnoreCase;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.support.Repositories;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Implementa a validação de valor único ignorando maiúsculas e minúsculas.
 *
 * A validação considero os cenários de criação e edição da entidade. No primeiro,
 * a entidade não existe no banco de dados, por isso basta verificar se o valor
 * do atributo está cadastrado; no segundo, a entidade está cadastrada no banco,
 * e portanto, deve-se verificar se o valor do atributo não está cadastrado no banco
 * e é diferente do valor já contido na entidade.
 *
 */
@RequiredArgsConstructor
public class UniqueIgnoreCaseValidator implements ConstraintValidator<UniqueIgnoreCase, Object> {

  private static final String ID_FIELD = "id";
  private final Logger logger;

  /**
   * Nome do método do repositório usado para pesquisar a entidade,
   * o %s será substituído com o nome do atributo, com a primeira letra
   * em maiúscula.
   */
  private static final String METHOD_FORMAT = "findBy%sIgnoreCase";

  // Classe utilitária injetada pelo Spring, para recuperar repositórios
  private final Repositories repositories;

  // Referência para a interface do repositório correspondente a entidade
  private Class<?> repositoryClass;

  // instância do repositório
  private Repository repository;

  private String field;
  private Class<?> fieldType;

  private String methodName;

  @Override
  public void initialize(UniqueIgnoreCase constraintAnnotation) {

    this.logger.debug("[SIGTV] Inicializando validator: {}", constraintAnnotation);

    // recupera os valores passados na annotation
    Class<?> entityClass = constraintAnnotation.entity();
    this.field = constraintAnnotation.field();

    // recupera o tipo do atributo informado
    try {
      this.fieldType = entityClass.getDeclaredField(this.field).getType();
    } catch (NoSuchFieldException e) {
      throw new RuntimeException(e);
    }

    // monta o nome do repositório a partir do nome do atributo informado
    this.methodName = String.format(METHOD_FORMAT, StringUtils.capitalize(field));

    // recupera uma instância do repositório
    this.repository = (Repository) repositories.getRepositoryFor(entityClass).get();

    // recupera uma referência para a interface do repositório
    this.repositoryClass = this.repositories
        .getRepositoryInformationFor(entityClass)
        .get()
        .getRepositoryInterface();

  }

  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context) {

    this.logger.debug("[SIGTV] Validando objeto: {}", obj);

    try {

      // recupera dinamicamente os métodos get para o id e para o atributo que
      // se deseja validar
      Method getId = PropertyUtils.getPropertyDescriptor(obj, ID_FIELD).getReadMethod();
      Method getField = PropertyUtils.getPropertyDescriptor(obj, this.field).getReadMethod();

      // recupera os valores do id e do atributo
      Long id = (Long) this.invokeMethod(getId, obj);
      Object fieldValue = this.invokeMethod(getField, obj);

      // obtém uma referência para o método do repositório
      Method method = this.repositoryClass.getMethod(this.methodName, this.fieldType);

      // Consulta o repositório usando o método recuperado e passando o valor do atributo
      Optional<Object> foundObj = (Optional<Object>) this.invokeMethod(method,this.repository, fieldValue);

      // verifica se o id do objeto recuperado é igual ao id do objeto sob validação
      boolean valid = foundObj.map(e -> this.invokeMethod(getId, e).equals(id)).orElse(true);

      // Se a validação falhou, customiza a mensagem de erro
      if (!valid) {
        context.disableDefaultConstraintViolation();

        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
            .addPropertyNode(this.field )
            .addConstraintViolation();
      }

      return valid;

    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      this.logger.error("[SIGTV] {}", e.getMessage(), e);
      throw new RuntimeException(e);
    }

  }

  /**
   * Esse é um método auxiliar para facilitar o tratamento de exceção dentro
   * da expressão lambda. Sua função é simplesmente capturar as exceções checadas
   * e relançá-las como RuntimeException. Os parâmetros seguem a mesma estrutura
   * do método invoke da classe Method, precedidos da referência do método em sim.
   *
   */
  private Object invokeMethod(Method method, Object obj, Object... args) {
    try {
      return method.invoke(obj, args);
    } catch (IllegalAccessException | InvocationTargetException e) {
      this.logger.error("[SIGTV] {}", e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }
}
