package com.akbank.jpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.akbank.jpa.entities.Supplier;
import com.akbank.jpa.repositories.SupplierRespository;

@Controller
@RequestMapping("/")
public class SupplierController {

  // @Qualifier("supplierRepositoryImp") // isimden ilgili sınıfı dependency
  // injection ile ulaşmamızı sağlayacak bir anotasyon.
  private SupplierRespository supplierRepo;

  public SupplierController(SupplierRespository supplierRepo) {
    super();
    this.supplierRepo = supplierRepo;
  }

  @GetMapping("supplier-create")
  public void CreateTest() {

    var sp = new Supplier();
    sp.setCompanyName("company1");
    sp.setContactName("contact1");
    this.supplierRepo.create(sp);
  }

  @GetMapping("supplier-update")
  public void UpdateTest() {

    var sp = supplierRepo.findById(1); // attached
    sp.setCompanyName("company11");
    sp.setContactName("contact11");

    this.supplierRepo.update(sp);

  }

  @GetMapping("supplier-delete")
  public void DeleteTest() {

    supplierRepo.delete(31); // attached

  }

  @GetMapping("supplier-delete-with-transaction")
  public void DeleteManuelTransactionTest() {

    supplierRepo.deleteManuelTransaction(32); // attached

  }

  @GetMapping("supplier-list")
  @ResponseBody
  public List<Supplier> ListTest() {

    // return supplierRepo.findAllWithCriteriaApi();
    return supplierRepo.findAll();
  }

}
