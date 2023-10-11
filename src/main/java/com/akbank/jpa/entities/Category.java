package com.akbank.jpa.entities;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CategoryID")
  private int id;

  @Column(name = "CategoryName", nullable = false)
  private String name; // property ismini istediğimiz gibi seçebiliriz

  // mappedBy diğer tablodaki source relation propery tanımlıyoruz
  // bunu eklmez isek relation doğru kuramıyor
  @JsonBackReference // circular referance yönetimini yap // mappedBy category olduğu için burada
                     // back referance atribute tanımı yaptık
  @OneToMany(mappedBy = "category")
  private List<Product> products; // aynı referance sahip bir nesne varsa set bunun eklenmesini önler

}
