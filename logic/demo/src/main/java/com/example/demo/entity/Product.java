package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

  @Column(nullable = false)
  private String name;

  private String image;

  @Column(nullable = false)
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

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

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return id.equals(product.id) && name.equals(product.name) && Objects.equals(image, product.image)
        && Objects.equals(price, product.price) && Objects.equals(category,
        product.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, image, price, category);
  }
}