package com.example.demo.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class CategoryDto implements Serializable {

  private static final Long serialVersionUUID = 1L;

  private Long id;
  private String name;

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
    CategoryDto that = (CategoryDto) o;
    return id.equals(that.id) && name.equals(that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}