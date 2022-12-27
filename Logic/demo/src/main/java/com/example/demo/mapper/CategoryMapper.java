package com.example.demo.mapper;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {

  CategoryDto categoryToDto(Category category);
}
