package com.example.demo.factory;

import org.springframework.stereotype.Service;

import com.example.demo.gen.Response;

@Service
public class ResponseFactory {
	
	public Response make(String data, String error) {
		Response response = new Response();
		response.setData(error == null ? data : "");
		response.setError(error);
		return response;
	}
}