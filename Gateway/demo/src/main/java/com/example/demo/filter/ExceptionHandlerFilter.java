package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.ResponseFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

	@Autowired
	private ResponseFactory responseFactory;
	
	private static final Logger logger = LogManager.getLogger(ExceptionHandlerFilter.class);

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (Throwable e) {

			ObjectMapper mapper = new ObjectMapper();

			StringBuilder error = new StringBuilder();

			if (e.getMessage() != null) {
				error.append(" " + e.getMessage() + "\n");
			}
			if (e.getCause() != null) {
				if (e.getCause().getMessage() != null) {
					error.append(e.getCause().getMessage() + "\n");
				}
				if (e.getCause().getCause() != null) {
					if (e.getCause().getCause().getMessage() != null) {
						error.append(e.getCause().getCause().getMessage() + "\n");
					}
				}
			}
			
			String errorMessage = error.toString();
			
			logger.error("ERROR", e);
			
			var responseData = this.responseFactory.make("", errorMessage);

			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.getWriter().write(mapper.writeValueAsString(responseData));
		}
	}
}