package com.example.demo.mq.request;

import com.example.demo.contract.Jsonable;

public class ProductListRequest implements Jsonable {
	
	private String searchQuery;
	
	private String category;
	
	public String getSearchQuery() {
		return this.searchQuery;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
}