package com.example.demo.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;

@Service
public class TokenService {

	    @Value("${jwt.secret}")
	    private String secretKey;

	    public Claims getClaimsFromToken(String token) throws ExpiredJwtException {
	    	JwtParser parser = Jwts.parser();
	    	parser.setSigningKey(secretKey);
	    	Jws<Claims> claims = parser.parseClaimsJws(token);
	    	return claims.getBody();
	    }

	    public String generateToken(String email) {
	        Map<String, Object> claims = new HashMap<>();
	        var currentTime = System.currentTimeMillis();
	        return Jwts.builder()
	        		.setClaims(claims)
	        		.setSubject(email)
	        		.setIssuedAt(new Date(currentTime))
	                .setExpiration(new Date(currentTime + 86_400_000))
	                .signWith(SignatureAlgorithm.HS512, secretKey)
	                .compact();
	    }
	    
	    public String pluckEmail(String token) throws ExpiredJwtException {
	    	return this.getClaimsFromToken(token).getSubject();
	    }
}
