package com.akbank.jpa.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ProductID")
  private int id;

  @Column(name = "ProductName", nullable = false)
  private String name;

  @Column(name = "UnitPrice")
  private float unitPrice;

  @Column(name = "UnitsInStock")
  private int unitsInStock;

  // EAGER ise direkt olarak ilişki tabloları joinler tek bir sql sorgusu ile
  // çalışır
  @ManyToOne() // defaulda ürünleri sadece sorgular eğer
  // ekrana ürünün categorysinin ismi
  // yazılacak ise o ismi yazılacak olan ürüne ait categoryde arkan planda
  // sorgulayıp ekrana alt nesnenin eklenmesine sağlar. birden fazlas sorgu atarak
  // çalışır
  // Fk için bir field açmıyoruz. Çünkü bu field olmadan da veritabından FK
  // üzerinden sorgu çalıştırabiliyoruz.
  @JsonManagedReference // managed entity olduğundan dolayı ekledik
  @JoinColumn(name = "CategoryId", nullable = true)
  private Category category;

}
