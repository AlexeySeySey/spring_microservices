package com.example.demo.entity;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
  private Role role;

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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return id.equals(user.id) && email.equals(user.email) && Objects.equals(password, user.password)
        && Objects.equals(role, user.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, password, role);
  }
}