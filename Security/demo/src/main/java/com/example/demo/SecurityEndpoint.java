package com.example.demo;

import com.example.demo.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SecurityEndpoint {

  private static final String NAMESPACE_URI = "http://localhost:8080/demo/gen";

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRegistrationRequest")
  @ResponsePayload
  public GetRegistrationResponse getRegistrationRequest(@RequestPayload GetRegistrationRequest request) {

    var response = new Response();
    response.setData(String.format("Email:%s\nPassword:%s", request.getEmail(), request.getPassword()));
    response.setError("bar");

    var registrationResponse = new GetRegistrationResponse();
    registrationResponse.setResponse(response);

    return registrationResponse;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLoginRequest")
  @ResponsePayload
  public GetLoginResponse getLoginRequest(@RequestPayload GetLoginRequest request) {

    var response = new Response();
    response.setData(String.format("Email:%s\nPassword:%s", request.getEmail(), request.getPassword()));
    response.setError("bar");

    var loginResponse = new GetLoginResponse();
    loginResponse.setResponse(response);

    return loginResponse;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLogoutRequest")
  @ResponsePayload
  public GetLogoutResponse getLogoutRequest(@RequestPayload GetLogoutRequest request) {

    var response = new Response();
    response.setData(String.format("Token:%s", request.getToken()));
    response.setError("bar");

    var logoutResponse = new GetLogoutResponse();
    logoutResponse.setResponse(response);

    return logoutResponse;
  }
}