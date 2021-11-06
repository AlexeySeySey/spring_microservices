package com.example.demo.Unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.example.demo.factory.ResponseFactory;
import com.example.demo.gen.Response;

public class ResponseFactoryTest {
	
	@Test
	public void withoutError() {
		
		String data = "foo";
		String error = null;
		
		Response response = new ResponseFactory().make(data, error);
		
		Assertions.assertEquals(response.getData(), data);
		Assertions.assertEquals(response.getError(), error);
	}
	
	@Test
	public void withError() {
		
		String data = "foo";
		String error = "bar";
		
		Response response = new ResponseFactory().make(data, error);
		
		Assertions.assertEquals(response.getData(), "");
		Assertions.assertEquals(response.getError(), error);
	}
}