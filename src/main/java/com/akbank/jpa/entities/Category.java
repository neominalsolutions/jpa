package com.akbank.jpa.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Categories")
@Data
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CategoryID")
  private int id;

  @Column(name = "CategoryName")
  private String categoryName;

  // Json Circular Serialization hatasını önlemek için koyduk

  // @JsonManagedReference
  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  @JsonBackReference
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<Product> products;

}
