package com.akbank.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Suppliers")
@Data
public class Supplier {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SupplierID")
  private int id;

  @Column(name = "CompanyName", nullable = false)
  private String companyName;

  @Column(name = "ContactName", nullable = true)
  private String contactName;
}
