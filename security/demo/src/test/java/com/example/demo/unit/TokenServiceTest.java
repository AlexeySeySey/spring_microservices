package com.example.demo.unit;

import com.example.demo.service.TokenService;
import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenServiceTest {

  @Autowired
  private TokenService tokenService;

  @Test
  public void getClaimsFailed() {
    Assertions.assertThrows(JwtException.class, () -> tokenService.getClaimsFromToken("test"));
  }

  @Test
  public void generateTokenSuccess() {

    String token = tokenService.generateToken("foo@bar.baz");

    tokenService.getClaimsFromToken(token);
  }

  @Test
  public void pluckEmailSuccess() {

    String email = "foo@i.com";

    String token = tokenService.generateToken(email);

    Assertions.assertEquals(email, tokenService.pluckEmail(token));
  }
}