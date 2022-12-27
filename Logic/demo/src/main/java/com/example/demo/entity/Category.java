package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

  @Column(name = "name", nullable = false)
  private String name;

  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  private List<Product> products = new ArrayList<>();

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public List<Product> getProducts() {
    return products;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}