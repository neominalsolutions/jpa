package com.akbank.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.akbank.jpa.entities.Product;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  List<Product> findByName(String name);

  // invalid bir çağrı
  // List<Product> getProductName(String name);

  List<Product> findByNameLike(String name);

  List<Product> findByNameStartsWith(String name);

  List<Product> findByNameEndsWith(String name);

  List<Product> findByNameAndUnitPrice(String name, float unitPrice);

  // Greather Than
  List<Product> findByUnitPriceGreaterThan(float unitprice);

  // Less Than
  List<Product> findByUnitPriceLessThan(float unitprice);

  List<Product> findByUnitPriceBetween(int start, int end);

  List<Product> findByOrderByNameAsc();

  List<Product> findByOrderByNameAscUnitPriceDesc();

  @Query("SELECT DISTINCT e FROM Product e INNER JOIN FETCH e.category t")
  List<Product> findByWithCategories();

}
