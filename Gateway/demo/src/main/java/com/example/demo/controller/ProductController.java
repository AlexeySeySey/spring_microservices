package com.example.demo.controller;

import com.example.demo.client.CountryClient;
import com.example.demo.service.ResponseFactory;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

  private ResponseFactory responseFactory;

  private CountryClient countryClient;

  @Autowired
  public ProductController(
      ResponseFactory responseFactory,
      CountryClient countryClient
  ) {
    this.responseFactory = responseFactory;
    this.countryClient = countryClient;
  }

  @GetMapping("/list")
  public Object list() {
    // TODO: get products including search, pagination, and picking products by category
    return this.countryClient.getCountry("Spain");
  }

  @PostMapping("/create")
  public String create() {
    // TODO: admin creates new product
    return "";
  }

  @PutMapping("/update")
  public String update() {
    // TODO: admin edits product, it can be restored here
    return "";
  }

  @DeleteMapping("/remove")
  public String remove() {
    // TODO: admin deletes product softly
    return "";
  }

  @PostMapping("/import")
  public String importProducts() {
    // TODO: admin imports Excel document with products in it
    return "";
  }

  @GetMapping("/export")
  public String exportProducts() {
    // TODO: admin gets excel document with products in it, by filters
    return "";
  }
}
