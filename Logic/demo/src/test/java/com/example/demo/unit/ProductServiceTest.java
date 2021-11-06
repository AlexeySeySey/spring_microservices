package com.example.demo.unit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	
	@InjectMocks
	private ProductService productService;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private Query q;
	
	@Test
	public void findAll() {
		
		Product product = new Product();
		product.setId(Long.valueOf(1));
		product.setName("foo");
		product.setPrice(BigDecimal.valueOf(100));
		
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(product);
		
		Mockito.when(q.getResultList()).thenReturn(products);
		Mockito.when(em.createNativeQuery("select * from " + Product.TABLE + " order by created_at ASC")).thenReturn(q);
		
		List<Product> results = productService.findAll("", "");
		
		Assertions.assertFalse(results.isEmpty());
		Assertions.assertEquals(results.size(), 1);
		Assertions.assertEquals(results.get(0), product);
	}
	
	@Test
	public void searchByNameAndCategory() {
		
		Product product = new Product();
		product.setId(Long.valueOf(1));
		product.setName("foo");
		product.setPrice(BigDecimal.valueOf(100));
		
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(product);
		
		Mockito.when(q.getResultList()).thenReturn(products);
		Mockito.when(em.createNativeQuery("select * from " + Product.TABLE + " where name LIKE ?1 and category_id = ?2 order by created_at ASC")).thenReturn(q);
		
		List<Product> results = productService.findAll("someName", "someCategory");
		
		Assertions.assertFalse(results.isEmpty());
		Assertions.assertEquals(results.size(), 1);
		Assertions.assertEquals(results.get(0), product);
	}
}