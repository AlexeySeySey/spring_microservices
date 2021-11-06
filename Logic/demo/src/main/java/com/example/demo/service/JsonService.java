package com.example.demo.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class JsonService {

	public String encode(List items) {

		Type listType = new TypeToken<List>() {
		}.getType();

		return new Gson().toJson(items, listType);
	}
	
	public String encode(Object data) {
		return new Gson().toJson(data);
	}

	public Map<String, String> decode(String json) {

		Type type = new TypeToken<Map<String, String>>() {
		}.getType();

		return new Gson().fromJson(json, type);
	}
}