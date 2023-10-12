package com.akbank.jpa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.akbank.jpa.entities.Category;
import com.akbank.jpa.entities.Product;
import com.akbank.jpa.repositories.CategoryRepository;
import com.akbank.jpa.repositories.ProductRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Controller
@RequestMapping("/")
public class QueryController {

  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  public QueryController(ProductRepository productRepository) {
    super();
    this.productRepository = productRepository;
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
    // var plist = productRepository.findAll();

    // plist = productRepository.findByName("chai");

    // plist = productRepository.findByNameAndUnitPrice("chai", 20);

    // plist = productRepository.findByUnitPriceBetween(10, 100);

    // plist = productRepository.findByOrderByNameAscUnitPriceDesc();

    var plist = productRepository.findByWithCategories();

    return plist;
  }

  @GetMapping("categories")
  @ResponseBody // Json olarak result verebiliriz
  public List<Category> Categories() {

    // findById sorgusunda Eager olarak döndürdük.

    // var clist = categoryRepository.findById(1);

    // var clist = categoryRepository.findAll();

    // clist = categoryRepository.findAllNativeQuery();

    // clist = categoryRepository.findAllJpql();

    // clist = categoryRepository.findByCategoryNameLike("Bev");

    // clist = categoryRepository.findByCategoryNameLikeNamed("sea");

    // clist = categoryRepository.findByCategoryNameEqual("Produce");

    // clist = categoryRepository.findCategoryWithProducts();

    var clist = categoryRepository.findCategoryWithProductsNativeQuery();

    // var clist2 = categoryRepository.findCategoryWithProducts();

    // clist2 = categoryRepository.findCategoryWithProductsNativeQuery();

  

    System.out.println("productSize: " + clist.get(3).getProducts().size());

    return clist;
  }

}
