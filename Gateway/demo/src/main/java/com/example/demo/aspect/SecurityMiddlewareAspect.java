package com.example.demo.aspect;

import com.example.demo.constant.Error;
import com.example.demo.security.SecurityClient;
import java.util.Optional;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class SecurityMiddlewareAspect {

  private final SecurityClient securityClient;

  @Autowired
  public SecurityMiddlewareAspect(SecurityClient securityClient) {
    this.securityClient = securityClient;
  }

  @Pointcut("@annotation(com.example.demo.annotation.Protected)")
  public void check() {
  }

  @Before("check()")
  public void runCheck(JoinPoint point) throws Exception {

    String authHeader = Optional.ofNullable(RequestContextHolder.getRequestAttributes())
        .map(ServletRequestAttributes.class::cast)
        .map(ServletRequestAttributes::getRequest)
        .map(r -> r.getHeader("Authorization"))
        .orElseThrow(() -> new Exception(Error.UNAUTHORIZED.get()));

    String token = authHeader.replace("Bearer:", "")
        .replace("\\s", "")
        .trim();

    MethodSignature signature = (MethodSignature) point.getSignature();

    String access = String.format("%s.%s", point.getTarget().getClass().getSimpleName(),
        signature.getMethod().getName());

    String error = securityClient.getAccess(token, access)
        .getResponse().getError();

    if (error != null) {
      throw new Exception(error);
    }
  }
}