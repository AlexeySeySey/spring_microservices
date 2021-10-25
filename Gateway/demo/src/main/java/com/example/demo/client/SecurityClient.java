package com.example.demo.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import com.example.demo.client.gen.*;

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