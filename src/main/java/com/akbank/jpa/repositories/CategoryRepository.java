package com.akbank.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akbank.jpa.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

  // JPQL sorgulama dili entity üzerinden sorgula
  // Native Sql sorgular

  @Query(value = "Select * from Categories", nativeQuery = true)
  List<Category> findAllNativeQuery();

  // Category entityName
  @Query("Select c from Category c") // default olarak JPQL çalışır
  List<Category> findAllJpql();

  // property ismi
  // ?1 gelicek olan parametrelerden 1.si
  @Query("Select c from Category c where c.name like %?1%")
  List<Category> findByCategoryNameLike(String name);

  @Query("Select c from Category c where c.name like %:name%")
  List<Category> findByCategoryNameLikeNamed(@Param("name") String name);

  @Query("Select c from Category c where c.name = :name")
  List<Category> findByCategoryNameEqual(@Param("name") String name);

  @Query("Select c from Category c left join c.products")
  List<Category> findCategoryWithProducts();

  // tablename Categories
  // @Query(value = "Select * from Categories join Products on
  // Categories.CategoryID = Products.CategoryID", nativeQuery = true)
  // List<Category> findCategoryWithProductsNativeQuery();

}
