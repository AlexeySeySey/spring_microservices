package com.example.demo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

  private static final Logger logger = LogManager.getLogger(ExceptionHandlerFilter.class);

  @Override
  public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain)
      throws IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (Throwable e) {
      logger.error("ERROR", e);
      ObjectMapper mapper = new ObjectMapper();
      response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.getWriter().write(mapper.writeValueAsString(e));
    }
  }
}