package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (Throwable e) {

			ObjectMapper mapper = new ObjectMapper();

			Throwable cause = e.getCause();
			String causeMessage = cause.getMessage();
			String error = causeMessage == null
					? cause.getCause().getMessage()
					: causeMessage;

			var responseData = this.responseFactory.make("", e.getCause().getMessage());

			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.getWriter().write(mapper.writeValueAsString(responseData));
		}
	}
}