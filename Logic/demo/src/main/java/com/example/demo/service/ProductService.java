package com.example.demo.service;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

import org.springframework.data.domain.Sort;

@Service
public class ProductService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public List<Product> findAll(String name, String category) {
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
		
		Root<Product> from = criteriaQuery.from(Product.class);
		
		CriteriaQuery<Object> select = criteriaQuery.select(from);
		
		criteriaQuery.where(criteriaBuilder.equal(criteriaBuilder.literal(1), 1));
		
		boolean nameEmpty = name == "" || name == null;
		if (!nameEmpty) {
			criteriaQuery.where(criteriaBuilder.like(from.get("name"), "%" + name + "%"));
		}
		
		boolean categoryEmpty = category == "" || category == null;
		if (!categoryEmpty) {
			criteriaQuery.where(criteriaBuilder.equal(from.get("category").get("id"), category));
		}
		
		TypedQuery<Object> typedQuery = em.createQuery(select);
		
		return (List<Product>) typedQuery.getResultList().stream().map(Product.class::cast).collect(Collectors.toList());
	}
}