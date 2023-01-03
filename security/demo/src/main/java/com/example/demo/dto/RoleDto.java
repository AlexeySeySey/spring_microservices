package com.example.demo.dto;

import java.time.LocalDate;
import java.util.Set;

public class RoleDto {

  private Long id;
  private String name;
  Set<PermissionDto> permissions;
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

  public Set<PermissionDto> getPermissions() {
    return permissions;
  }

  public void setPermissions(Set<PermissionDto> permissions) {
    this.permissions = permissions;
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
}
