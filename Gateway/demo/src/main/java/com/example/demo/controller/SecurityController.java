package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.SecurityClient;
import com.example.demo.client.gen.GetLoginRequest;
import com.example.demo.client.gen.GetRegistrationRequest;
import com.example.demo.client.gen.Response;
import com.example.demo.service.ResponseFactory;
import com.example.demo.service.ServletService;

@RestController
@RequestMapping("/security")
public class SecurityController {
	
  @Autowired	
  private SecurityClient securityClient;
  
  @Autowired
  private ServletService servletService;
  
  @Autowired
  private ResponseFactory responseFactory;

  @PostMapping("/register")
  public Map<String, Object> register(@RequestBody GetRegistrationRequest registrationRequest) {
	  
    Response response = this.securityClient
    		.register(registrationRequest.getEmail(), registrationRequest.getPassword())
    		.getResponse();
    
    return this.responseFactory.make(response.getData(), response.getError());
  }

  @PostMapping("/signin")
  public Map<String, Object> signin(@RequestBody GetLoginRequest loginRequest) {
	  
    Response response = this.securityClient
    		.signin(loginRequest.getEmail(), loginRequest.getPassword())
    		.getResponse();

    return this.responseFactory.make(response.getData(), response.getError());
  }
}
