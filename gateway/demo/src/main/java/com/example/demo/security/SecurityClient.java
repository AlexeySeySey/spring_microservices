package com.example.demo.security;

import com.example.demo.security.gen.GetAccessRequest;
import com.example.demo.security.gen.GetAccessResponse;
import com.example.demo.security.gen.GetLoginRequest;
import com.example.demo.security.gen.GetLoginResponse;
import com.example.demo.security.gen.GetRegistrationRequest;
import com.example.demo.security.gen.GetRegistrationResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class SecurityClient extends WebServiceGatewaySupport {

  public GetAccessResponse getAccess(String token, String access) {

    GetAccessRequest request = new GetAccessRequest();
    request.setToken(token);
    request.setAccess(access);

    return (GetAccessResponse) getWebServiceTemplate().marshalSendAndReceive(request);
  }

  public GetRegistrationResponse register(String email, String password) {

    GetRegistrationRequest request = new GetRegistrationRequest();
    request.setEmail(email);
    request.setPassword(password);

    return (GetRegistrationResponse) getWebServiceTemplate().marshalSendAndReceive(request);
  }

  public GetLoginResponse signin(String email, String password) {

    GetLoginRequest request = new GetLoginRequest();
    request.setEmail(email);
    request.setPassword(password);

    return (GetLoginResponse) getWebServiceTemplate().marshalSendAndReceive(request);
  }
}