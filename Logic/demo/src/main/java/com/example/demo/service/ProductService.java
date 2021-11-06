package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

import com.example.demo.repository.ProductRepository;

import org.springframework.data.domain.Sort;

@Service
public class ProductService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public List<Product> findAll(String name, String category) {
		
		boolean nameEmpty = name == "" || name == null;
		boolean categoryEmpty = category == "" || category == null;
		
		StringBuilder query = new StringBuilder("select * from " + Product.TABLE);
		if (!(nameEmpty && categoryEmpty)) {
			
			query.append(" where");
			
			if (name != null) {
				query.append(" name LIKE ?1");
			}
			if (category != null) {
				query.append(name != null ? " and" : " ");
				query.append(" category_id = ?2");
			}
		}
		
		query.append(" order by created_at ASC");
		
		Query q = em.createNativeQuery(query.toString());
		
		if (!nameEmpty) {
			q.setParameter(1, "%"+name+"%");
		}
		if (!categoryEmpty) {
			q.setParameter(2, category);
		}
		
		System.out.println("PRODUCTS: ");
		System.out.println(q.getResultList());

		List<Product> products = q.getResultList();
		
		return products;
	}
}