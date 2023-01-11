package com.example.demo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class ProductDto implements Serializable {

  private static final Long serialVersionUUID = 1L;

  private Long id;
  private String name;
  private String image;
  private BigDecimal price;

  private LocalDate createdAt;
  private LocalDate updatedAt;

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

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDate getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDate updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductDto that = (ProductDto) o;
    return id.equals(that.id) && name.equals(that.name) && Objects.equals(image, that.image)
        && Objects.equals(price, that.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, image, price);
  }
}
