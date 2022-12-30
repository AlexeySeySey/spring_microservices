package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

  ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

  @PersistenceContext
  private EntityManager em;

  @Transactional(isolation = Isolation.READ_COMMITTED)
  public List<ProductDto> findAll(String name, String category) {

    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
    Root<Product> from = criteriaQuery.from(Product.class);

    CriteriaQuery<Object> select = criteriaQuery.select(from);
    criteriaQuery.where(criteriaBuilder.equal(criteriaBuilder.literal(1), 1));

    if (StringUtils.isNotBlank(name)) {
      criteriaQuery.where(criteriaBuilder.like(from.get("name"), "%" + name + "%"));
    }
    if (StringUtils.isNotBlank(category)) {
      criteriaQuery.where(criteriaBuilder.equal(from.get("category").get("id"), category));
    }

    List<Product> products = em.createQuery(select)
        .getResultList()
        .stream()
        .map(Product.class::cast)
        .collect(Collectors.toList());

    return productMapper.productsToDtos(products);
  }
}