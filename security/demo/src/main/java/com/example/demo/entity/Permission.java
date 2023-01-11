package com.example.demo.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String name;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Permission that = (Permission) o;
    return id.equals(that.id) && name.equals(that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}