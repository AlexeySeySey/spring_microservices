package com.example.demo.dto;

import java.time.LocalDate;
import java.util.Objects;

public class UserDto {

  private Long id;
  private String email;
  private String password;
  private RoleDto role;

  private LocalDate createdAt;
  private LocalDate updatedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public RoleDto getRole() {
    return role;
  }

  public void setRole(RoleDto role) {
    this.role = role;
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
    UserDto userDto = (UserDto) o;
    return id.equals(userDto.id) && email.equals(userDto.email) && Objects.equals(password,
        userDto.password) && Objects.equals(role, userDto.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, password, role);
  }
}
