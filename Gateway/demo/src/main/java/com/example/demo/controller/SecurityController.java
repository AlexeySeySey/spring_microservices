package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

  @PostMapping("/register")
  public String register() {
    // TODO: user submit his data for registration, user gets mail on his mailbox
    return "";
  }

  @PostMapping("/sign-in")
  public String signIn() {
    // TODO: user submit his data to log in and get's a token string
    return "";
  }

  @PostMapping("/sign-out")
  public String signOut() {
    // TODO: user trying to logout from system
    return "";
  }

  @PostMapping("g-sign-in")
  public String googleSignIn() {
    // TODO: user trying to log in via Google account
    return "";
  }

  @PostMapping("recover-password")
  public String restorePassword() {
    // TODO: user clicks "Forget password?" and thus trying to restore his password
    return "";
  }
}
