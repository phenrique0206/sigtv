package br.gov.caixa.sigtv.util;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Order(1)
public class RequestLogFilter implements Filter {

  private final Logger logger;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain
  ) throws IOException, ServletException {

    this.logger.info("[SIGTV] Request {}", request);
    chain.doFilter(request, response);
    this.logger.info("[SIGTV] Response {}", response);

  }

  @Override
  public void destroy() {

  }
}
