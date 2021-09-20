package com.example.demo.entity;

import javax.persistance.*;
import lombok.*;

@Entity
@Builder
@Getter
@Table(name=Role.TABLE)
public final class Role {

  public static final String TABLE = "roles";

  @Id
  @GeneratedValue
  @Column(name="id", nullable=false)
  private Long id;

  @Column(name="name", nullable=false, unique=true, lenght=255, columnDefinition="VARCHAR")
  private String name;
}