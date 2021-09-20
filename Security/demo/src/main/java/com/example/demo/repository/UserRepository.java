package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.User;

@Repository
public final interface UserRepository implements JpaRepository<User, Long> {
  //
}