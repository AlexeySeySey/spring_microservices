package com.example.demo.Unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import com.example.demo.WebServiceConfig;
import com.example.demo.service.TokenService;
import io.jsonwebtoken.JwtException;

@SpringBootTest
public class TokenServiceTest {
	
	@Autowired
	private TokenService tokenService;
	
	@Test
	public void getClaimsFailed() {
		
		Executable operation = () -> this.tokenService.getClaimsFromToken("test");
		
		Assertions.assertThrows(JwtException.class, operation);
	}
	
	@Test
	public void generateTokenSuccess() {
		
		String token = this.tokenService.generateToken("foo@bar.baz");
		
	    this.tokenService.getClaimsFromToken(token);
	}
	
	@Test
	public void pluckEmailSuccess() {
		
		String email = "foo@i.com";
		
		String token = this.tokenService.generateToken(email);
		
		String pluckedEmail = this.tokenService.pluckEmail(token);
		
		Assertions.assertEquals(email, pluckedEmail);
	}
}