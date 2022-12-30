package com.example.demo;

import com.example.demo.constant.Error;
import com.example.demo.constant.Message;
import com.example.demo.dto.PermissionDto;
import com.example.demo.dto.UserDto;
import com.example.demo.factory.ResponseFactory;
import com.example.demo.gen.GetAccessRequest;
import com.example.demo.gen.GetAccessResponse;
import com.example.demo.gen.GetLoginRequest;
import com.example.demo.gen.GetLoginResponse;
import com.example.demo.gen.GetRegistrationRequest;
import com.example.demo.gen.GetRegistrationResponse;
import com.example.demo.gen.Response;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import io.jsonwebtoken.JwtException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SecurityEndpoint {

  public final static String NAMESPACE_URI = "http://localhost:8080/demo/gen";

  private final UserService userService;

  private final TokenService tokenService;

  private final ResponseFactory responseFactory;

  @Autowired
  public SecurityEndpoint(
      UserService userService,
      TokenService tokenService,
      ResponseFactory responseFactory
  ) {
    this.userService = userService;
    this.tokenService = tokenService;
    this.responseFactory = responseFactory;
  }

  private static final Logger logger = LogManager.getLogger(SecurityEndpoint.class);

  @ResponsePayload
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRegistrationRequest")
  public GetRegistrationResponse getRegistrationRequest(
      @RequestPayload GetRegistrationRequest request) {

    String error = "";
    try {
      userService.registerUser(request.getEmail(), request.getPassword());
    } catch (Exception e) {
      error = e.getMessage();
      logger.error(Message.ERROR.get(), e);
    }

    Response response = responseFactory.make(Message.REGISTRATION_SUCCEED.get(), error);
    GetRegistrationResponse registrationResponse = new GetRegistrationResponse();
    registrationResponse.setResponse(response);
    return registrationResponse;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLoginRequest")
  @ResponsePayload
  public GetLoginResponse getLoginRequest(@RequestPayload GetLoginRequest request) {

    String token = null;
    String error = null;
    try {

      UserDto user = userService.findByEmail(request.getEmail())
          .orElseThrow(() -> new Exception(Error.UNREGISTERED_USER.get()));

      if (!userService.passwordsSame(request.getPassword(), user.getPassword())) {
        throw new Exception(Error.UNREGISTERED_USER.get());
      }

      token = tokenService.generateToken(request.getEmail());

    } catch (Exception e) {
      error = e.getMessage();
      logger.error(Message.ERROR.get(), e);
    }

    Response response = responseFactory.make(token, error);
    GetLoginResponse loginResponse = new GetLoginResponse();
    loginResponse.setResponse(response);
    return loginResponse;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccessRequest")
  @ResponsePayload
  public GetAccessResponse getAccessRequest(@RequestPayload GetAccessRequest request) {

    String error = "";
    try {

      String email = "";
      try {
        email = tokenService.pluckEmail(request.getToken());
      } catch (JwtException e) {
        throw new Exception(Error.UNAUTHORIZED.get());
      }

      UserDto user = userService.findByEmail(email)
          .orElseThrow(() -> new Exception(Error.UNREGISTERED_USER.get()));

      boolean accessAllowed = user.getRole().getPermissions().stream()
          .map(PermissionDto::getName)
          .anyMatch(request.getAccess()::equals);

      if (!accessAllowed) {
        throw new Exception(Error.FORBIDDEN.get());
      }

    } catch (Exception e) {
      error = e.getMessage();
      logger.error(Message.ERROR.get(), e);
    }

    Response response = responseFactory.make(Message.ACCESS_ALLOWED.get(), error);
    GetAccessResponse accessResponse = new GetAccessResponse();
    accessResponse.setResponse(response);
    return accessResponse;
  }
}