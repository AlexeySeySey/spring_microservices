package com.example.demo.contract;

import com.google.gson.Gson;

public interface Jsonable {
	
	public default String asJson() {
		
		return new Gson().toJson(this);
	}
}