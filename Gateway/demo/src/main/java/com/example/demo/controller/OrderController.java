package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

  @GetMapping("/list")
  public String list() {
    // TODO: admin can see all orders and can filter them by order status
    return "";
  }

  @PostMapping("/create")
  public String create() {
    // TODO: user's makes order for products in his card, user gets mail on his mailbox
    // TODO: User have ability to get delivery by his address and that's it, no NovaPoshta needed
    // TODO: User have possiblity to pre-pay for his order via payment system integration
    //       Transaction may be applied here, maybe....
    return "";
  }

  @PutMapping("/update")
  public String update() {
    // TODO: admin can change status of order to "declined",
    //       but he must provide description about it so it will be send
    //       to clients, or, if nothing provided - default message will be send
    //       Multiple orders can be declined
    return "";
  }
}
