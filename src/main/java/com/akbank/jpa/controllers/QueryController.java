package com.akbank.jpa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.akbank.jpa.entities.Category;
import com.akbank.jpa.entities.Product;
import com.akbank.jpa.repositories.CategoryRepository;
import com.akbank.jpa.repositories.ProductRepository;

@Controller
@RequestMapping("/")
public class QueryController {

  private ProductRepository productRepository;
  private CategoryRepository categoryRepository;

  public QueryController(ProductRepository productRepository, CategoryRepository categoryRepository) {
    super();
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
  }

  @GetMapping("productsWithPagination")
  @ResponseBody
  public Page<Product> PagedProducts() {

    var page = PageRequest.of(1, 10, Sort.by("name").ascending().and(Sort.by("unitPrice").descending()));
    return productRepository.findAll(page);

  }

  @GetMapping("products")
  @ResponseBody // Json olarak result verebiliriz
  public List<Product> Products() {
    var plist = productRepository.findAll();

    plist = productRepository.findByName("chai");

    plist = productRepository.findByNameAndUnitPrice("chai", 20);

    plist = productRepository.findByUnitPriceBetween(10, 100);

    plist = productRepository.findByOrderByNameAscUnitPriceDesc();

    return plist;
  }

  @GetMapping("categories")
  @ResponseBody // Json olarak result verebiliriz
  public List<Category> Categories() {
    var clist = categoryRepository.findAll();

    clist = categoryRepository.findAllNativeQuery();

    clist = categoryRepository.findAllJpql();

    clist = categoryRepository.findByCategoryNameLike("Bev");

    clist = categoryRepository.findByCategoryNameLikeNamed("sea");

    clist = categoryRepository.findByCategoryNameEqual("Produce");

    clist = categoryRepository.findCategoryWithProducts();

    // clist = categoryRepository.findCategoryWithProductsNativeQuery();

    return clist;
  }

}
