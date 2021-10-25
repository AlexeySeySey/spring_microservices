package com.example.demo.service;

import java.io.BufferedReader;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import com.example.demo.constant.Error;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class ServletService {

	public HttpServletRequest getCurrentRequest() throws Exception {

		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		
		if (requestAttributes instanceof ServletRequestAttributes) {
			return ((ServletRequestAttributes) requestAttributes).getRequest();
		}
		throw new Exception(Error.UNPROCESSABLE_REQUEST.get());
	}
}