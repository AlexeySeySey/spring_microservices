package com.example.demo.dto;

import java.util.Date;
import java.util.List;

public class CategoryDto {

  private Long id;
  private String name;
  private List<ProductDto> products;
  private Date createdAt;
  private Date updatedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<ProductDto> getProducts() {
    return products;
  }

  public void setProducts(List<ProductDto> products) {
    this.products = products;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
}