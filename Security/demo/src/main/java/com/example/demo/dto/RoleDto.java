package com.example.demo.dto;

import java.util.Date;
import java.util.Set;

public class RoleDto {

  private Long id;
  private String name;
  Set<PermissionDto> permissions;
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

  public Set<PermissionDto> getPermissions() {
    return permissions;
  }

  public void setPermissions(Set<PermissionDto> permissions) {
    this.permissions = permissions;
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
