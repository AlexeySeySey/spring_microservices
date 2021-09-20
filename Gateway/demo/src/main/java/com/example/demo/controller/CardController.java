package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

  @GetMapping("/list")
  public String list() {
    // TODO: show all card items
    return "";
  }

  @PostMapping("/add")
  public String add() {
    // TODO: user adds product to card
    return "";
  }

  @DeleteMapping("/remove")
  public String remove() {
    // TODO: user removes product from his card, can be multiple
    return "";
  }

  @PutMapping("/update")
  public String updateItem() {
    // TODO: user skus count for product/products in his card
    return "";
  }
}
