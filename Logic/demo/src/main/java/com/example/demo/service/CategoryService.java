package com.example.demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Category;
import com.example.demo.mq.KafkaEvent;
import com.example.demo.repository.CategoryRepository;

@Service
@PropertySource("/cache.properties")
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private static final Logger logger = LogManager.getLogger(CategoryService.class);
	
	@Cacheable("${cache.keys.categoriesList}")
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Category> findAll() {
		
		return categoryRepository.findAll();
	}
}