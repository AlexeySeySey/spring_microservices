package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

  @GetMapping("/list")
  public String list() {
    // TODO: get categories tree
    return "baz";
  }

  @PostMapping("/add")
  public String add() {
    // TODO: admin creates new category
    return "";
  }

  @PutMapping("/update")
  public String update() {
    // TODO: admin edit's category, it can be restored here
    return "";
  }

  @DeleteMapping("/remove")
  public String remove() {
    // TODO: admin trying to softly remove category
    return "";
  }
}
