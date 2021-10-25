package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Modifying
  @Query(value = "insert into " + User.TABLE + "(email, password) values(:email,:password)", nativeQuery = true)
  public void create(@Param("email") String email, @Param("password") String password);
 
  public List<User> findByEmail(String email);
}