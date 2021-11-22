package com.example.demo.mq.request;

import com.example.demo.contract.Jsonable;

public class ProductStoreRequest implements Jsonable {
	
	private String name;
	
	private String image;
	
	private String price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}