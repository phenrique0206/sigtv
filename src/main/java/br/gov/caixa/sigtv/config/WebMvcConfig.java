package br.gov.caixa.sigtv.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Value("${br.gov.caixa.sigtv.cors.allow_credentials}")
  private Boolean ALLOW_CREDENTIALS;

  @Value("${br.gov.caixa.sigtv.cors.allowed_origin}")
  private String ALLOWED_ORIGIN;

  @Value("${br.gov.caixa.sigtv.cors.allowed_headers}")
  private String ALLOWED_HEADER;

  @Value("${br.gov.caixa.sigtv.cors.allowed_methods}")
  private String ALLOWED_METHOD;

  @Value("${br.gov.caixa.sigtv.cors.path}")
  private String PATH;

  @Bean
  public FilterRegistrationBean corsFilter() {

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();

    config.setAllowCredentials(ALLOW_CREDENTIALS);
    config.addAllowedOrigin(ALLOWED_ORIGIN);

    Arrays.stream(ALLOWED_HEADER.split(",")).forEach(config::addAllowedHeader);
    Arrays.stream(ALLOWED_METHOD.split(",")).forEach(config::addAllowedMethod);

    source.registerCorsConfiguration(PATH, config);

    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
    bean.setOrder(0);

    return bean;
  }

  @Bean
  @Scope("prototype")
  public Logger logger(InjectionPoint injectionPoint){

    return LoggerFactory.getLogger(injectionPoint.getMethodParameter().getContainingClass());

  }

}