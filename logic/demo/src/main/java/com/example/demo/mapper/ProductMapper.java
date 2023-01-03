package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

  ProductDto productToDto(Product product);

  List<ProductDto> productsToDtos(List<Product> products);
}
