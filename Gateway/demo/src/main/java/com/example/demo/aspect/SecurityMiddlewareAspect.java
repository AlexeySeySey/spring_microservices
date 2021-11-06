package com.example.demo.aspect;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.constant.Error;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.client.SecurityClient;
import com.example.demo.client.gen.Response;
import com.example.demo.service.ServletService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Aspect
@Component
public class SecurityMiddlewareAspect {

	@Autowired
	private SecurityClient securityClient;

	@Autowired
	private ServletService servletService;

	@Pointcut("@annotation(com.example.demo.annotation.Protected)")
	public void check() {
	}

	@Before("check()")
	public void runCheck(JoinPoint point) throws Exception {

		var request = this.servletService.getCurrentRequest();
		
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		
		String authHeader = requestAttributes.getRequest().getHeader("Authorization");

		if (authHeader == null) {
			String error = Error.UNAUTHORIZED.get();
			throw new Exception(error);
		}

		String token = authHeader.replace("Bearer:", "")
				.replace("\\s", "")
				.trim();

		MethodSignature signature = (MethodSignature) point.getSignature();
		
		String access = String.format("%s.%s", point.getTarget().getClass().getSimpleName(), signature.getMethod().getName());

		Response response = this.securityClient.getAccess(token, access).getResponse();
		
		String error = response.getError();
		if (error != null) {
			throw new Exception(error);
		}
	}
}