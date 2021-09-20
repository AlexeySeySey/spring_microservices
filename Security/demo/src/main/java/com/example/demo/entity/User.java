package com.example.demo.entity;

import javax.persistance.*;
import java.util.ArrayList;
import lombok.*;

@Entity
@Builder
@Getter
@Table(name=User.TABLE)
public final class User {

  public static final String TABLE = "users";

  @Id
  @GeneratedValue
  @Column(name="id", nullable=false)
  private Long id;

  @Column(name="email", nullable=false, unique=true, length=255, columnDefinition="VARCHAR")
  private String email;

  @Column(name="password", nullable=false, length=255, columnDefinition="VARCHAR")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  private ArrayList<Role> roles = new ArrayList<Role>();
}