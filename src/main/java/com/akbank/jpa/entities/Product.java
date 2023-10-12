package com.akbank.jpa.entities;

import java.io.Serializable;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
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
import lombok.Data;

@Entity
@Data
@Table(name = "Products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ProductID")
  private int id;

  @Column(name = "ProductName", nullable = false)
  private String name;

  @Column(name = "UnitPrice", nullable = true)
  private Float unitPrice;

  @Column(name = "UnitsInStock", nullable = true)
  private Integer unitsInStock;

  @Column(name = "Discontinued", nullable = false)
  private Boolean discontinued;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "CategoryId", nullable = false)
  @JsonManagedReference
  // @JsonBackReference
  private Category category;

}
