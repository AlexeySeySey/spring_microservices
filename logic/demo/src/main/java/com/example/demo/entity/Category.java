package com.example.demo.entity;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "category")
  private List<Product> products;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Category category = (Category) o;
    return id.equals(category.id) && name.equals(category.name) && Objects.equals(products, category.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, products);
  }
}