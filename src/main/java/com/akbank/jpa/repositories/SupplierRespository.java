package com.akbank.jpa.repositories;

import java.util.List;

import com.akbank.jpa.entities.Supplier;

public interface SupplierRespository {

  void create(Supplier supplier);

  void update(Supplier supplier);

  void delete(int id);

  void deleteManuelTransaction(int id);

  List<Supplier> findAll();

  Supplier findById(int id);

  List<Supplier> findAllWithCriteriaApi();

}
