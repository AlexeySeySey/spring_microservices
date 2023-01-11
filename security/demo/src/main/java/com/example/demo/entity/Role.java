package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String name;

  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "role_permission", joinColumns = {@JoinColumn(name = "role_id")},
      inverseJoinColumns = {@JoinColumn(name = "permission_id")})
  List<Permission> permissions = new ArrayList<>();

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setPermissions(List<Permission> permissions) {
    this.permissions = permissions;
  }

  public List<Permission> getPermissions() {
    return permissions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Role role = (Role) o;
    return id.equals(role.id) && name.equals(role.name) && Objects.equals(permissions, role.permissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, permissions);
  }
}