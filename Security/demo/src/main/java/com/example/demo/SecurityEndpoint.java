package com.example.demo;

import com.example.demo.gen.GetRegistrationRequest;
import com.example.demo.gen.GetRegistrationResponse;
import com.example.demo.gen.GetLoginRequest;
import com.example.demo.gen.GetLoginResponse;
import com.example.demo.gen.GetAccessRequest;
import com.example.demo.gen.GetAccessResponse;
import com.example.demo.gen.Response;
import com.example.demo.factory.ResponseFactory;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import com.example.demo.exception.UserNotFoundException;

import io.jsonwebtoken.ExpiredJwtException;

import com.example.demo.constant.Error;
import com.example.demo.constant.Message;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import io.jsonwebtoken.JwtException;

@Endpoint
public class SecurityEndpoint {

	private static final String NAMESPACE_URI = "http://localhost:8080/demo/gen";

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private ResponseFactory responseFactory;

	private static final Logger logger = LogManager.getLogger(SecurityEndpoint.class);

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRegistrationRequest")
	@ResponsePayload
	public GetRegistrationResponse getRegistrationRequest(@RequestPayload GetRegistrationRequest request) {

		String error = null;

		try {
			String email = request.getEmail();
			String password = request.getPassword();

			User user = null;
			
			try {
				user = this.userService.findByEmail(email);
			} catch (UserNotFoundException e) {
			}
			
			if (user != null) {
				throw new Exception(Error.USER_REGISTERED.get());
			}

			this.userService.registerUser(email, password);

		} catch (Exception e) {
			error = e.getMessage();
			logger.error(Message.ERROR.get(), e);
		}

		Response response = this.responseFactory.make(Message.REGISTRATION_SUCCEED.get(), error);

		var registrationResponse = new GetRegistrationResponse();
		registrationResponse.setResponse(response);

		return registrationResponse;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLoginRequest")
	@ResponsePayload
	public GetLoginResponse getLoginRequest(@RequestPayload GetLoginRequest request) {

		String token = null;
		String error = null;

		try {
			String email = request.getEmail();

			try {
				
				User user = this.userService.findByEmail(email);
				
				boolean passwordsSame = this.userService.passwordsSame(user, request.getPassword());
				
				if (!passwordsSame) {
					throw new UserNotFoundException();
				}
				
			} catch (UserNotFoundException e) {
				throw new Exception(Error.UNREGISTERED_USER.get());
			}

			token = this.tokenService.generateToken(email);

		} catch (Exception e) {
			error = e.getMessage();
			logger.error(Message.ERROR.get(), e);
		}

		Response response = this.responseFactory.make(token, error);

		var loginResponse = new GetLoginResponse();
		loginResponse.setResponse(response);

		return loginResponse;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccessRequest")
	@ResponsePayload
	public GetAccessResponse getAccessRequest(@RequestPayload GetAccessRequest request) {

		String error = null;

		try {
			String token = request.getToken();
			String access = request.getAccess();
			String email = null;

			try {
				email = this.tokenService.pluckEmail(token);
			} catch (JwtException e) {
				throw new Exception(Error.UNAUTHORIZED.get());
			}

			User user = this.userService.findByEmail(email);

			boolean accessAllowed = user.getRole().getPermissions().stream()
					.anyMatch((permission) -> permission.getName().equals(access));

			if (!accessAllowed) {
				throw new Exception(Error.FORBIDDEN.get());
			}

		} catch (Exception e) {
			error = e.getMessage();
			logger.error(Message.ERROR.get(), e);
		}

		Response response = this.responseFactory.make(Message.ACCESS_ALLOWED.get(), error);

		var accessResponse = new GetAccessResponse();
		accessResponse.setResponse(response);

		return accessResponse;
	}
}