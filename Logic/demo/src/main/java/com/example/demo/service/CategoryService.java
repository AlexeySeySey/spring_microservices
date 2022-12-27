package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@PropertySource("/cache.properties")
public class CategoryService {

  private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Cacheable("${cache.keys.categoriesList}")
  @Transactional(isolation = Isolation.READ_COMMITTED)
  public List<CategoryDto> findAll() {
    return categoryRepository.findAll()
        .stream()
        .map(categoryMapper::categoryToDto)
        .collect(Collectors.toList());
  }
}