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
  @Query("Select c from Category c where c.categoryName like %?1%")
  List<Category> findByCategoryNameLike(String categoryName);

  @Query("Select c from Category c where c.categoryName like %:categoryName%")
  List<Category> findByCategoryNameLikeNamed(@Param("categoryName") String categoryName);

  @Query("Select c from Category c where c.categoryName = :categoryName")
  List<Category> findByCategoryNameEqual(@Param("categoryName") String name);

  @Query("Select c from Category c left join c.products p")
  List<Category> findCategoryWithProducts();

  // tablename Categories
  // Bug Fix
  @Query(value = "select c.* from Categories as c inner join Products as p on c.CategoryID= p.CategoryID ", nativeQuery = true)
  List<Category> findCategoryWithProductsNativeQuery();

}
