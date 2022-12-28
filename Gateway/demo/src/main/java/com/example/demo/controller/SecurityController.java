package com.example.demo.controller;

import com.example.demo.security.SecurityClient;
import com.example.demo.security.gen.GetLoginRequest;
import com.example.demo.security.gen.GetRegistrationRequest;
import com.example.demo.security.gen.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

  private final SecurityClient securityClient;

  @Autowired
  public SecurityController(SecurityClient securityClient) {
    this.securityClient = securityClient;
  }

  @PostMapping("/register")
  public Response signup(@RequestBody GetRegistrationRequest registrationRequest) {
    return securityClient
        .register(registrationRequest.getEmail(), registrationRequest.getPassword())
        .getResponse();
  }

  @PostMapping("/signin")
  public Response signin(@RequestBody GetLoginRequest loginRequest) {
    return securityClient
        .signin(loginRequest.getEmail(), loginRequest.getPassword())
        .getResponse();
  }
}
