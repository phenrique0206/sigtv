package br.gov.caixa.sigtv.arquivo.model;

import br.gov.caixa.sigtv.arquivo.controller.ArquivoController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

@Configuration
@RequiredArgsConstructor
public class ArquivoResourceSupport  {

  private static final String REL = "download";
  private static final String PATH = "arquivo/download";

  private final Logger logger;

  @Bean
  public ResourceProcessor<Resource<Arquivo>> processSingleResource() {

    return new ResourceProcessor<Resource<Arquivo>>() {

      @Override
      public Resource<Arquivo> process(Resource<Arquivo> resource) {

        ArquivoResourceSupport.this.logger.trace("[SIGTV] Adicionando link ao arquivo: {}", resource);

        Link link = ControllerLinkBuilder.linkTo(ArquivoController.class)
            .slash(PATH)
            .slash(resource.getContent().getUuid())
            .withRel(REL);

        resource.add(link);

        return resource;

      }
    };
  }

}





