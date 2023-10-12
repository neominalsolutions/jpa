package com.akbank.jpa.repositories;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.akbank.jpa.entities.Supplier;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Repository
@Primary
public class SupplierRepositoryImp implements SupplierRespository {
  // Hibernate connectionları entity erişimlerini session altından yönetir. Burada
  // hibernate session bilgisine erişmek ve veri tabanı işlemlerini yönetmek için
  // EntityManager bağlanırız. Net EF tarafında bu DbContext denk geliyor.

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public void create(Supplier supplier) {
    // unattached olan veri tabınında iliği olmayan bir nesnenin persist edilmesi
    // anlamına geliyor.
    entityManager.persist(supplier); // insert sorgusu execute eder. persist state ise added olarak işaretlenir
                                     // entity ekleyeceğini anlar.
  }

  @Override
  @Transactional
  public void update(Supplier supplier) {
    // not öncesinde ilgili entity findById ile bulunup özelliklerin güncellenemsi
    // gerekir yoksa hjibernate entity track edemez ve exception oluşur.
    // Supplier program POJO nesnesi veri tabınında ile ilişik olmak zorunda.
    // attached , merge state hibernate güncelleme anlamına geliyor.
    entityManager.merge(supplier); // update sorgusu
  }

  @Override
  @Transactional
  public void delete(int id) {
    // TODO Auto-generated method stub
    var entity = findById(id); // attached

    if (entity == null) {
      throw new EntityNotFoundException("Supplier nesnesi bulunamadı");
    }

    entityManager.remove(entity); // state olarak remove delete sorgusu tetikler.

    // entity hibernate session üzerinden bağlantısını koparıp takip mekanizmasını
    // devreden çıkarmak için kullanılan state.
    // find gibi query sorgularında performans amaçlı entity detach edip nenseyi
    // sorgulayabilir.
    // entityManager.detach(entityManager);
  }

  // unchecked exception dediğimiz sistem tarafından oluşna exceptionları dikkate
  // alıyor.
  // developer sınıf içerisinde custom bir exception fırlatıyorsa bu exception
  // transaction'ın rollback olmasını sağlamaz.
  // tüm hatalar runtime ve exception base sınıflarından türediği için.
  // bu sayede developer exceptionları yakalam fırsatı bulduk.
  @Transactional(value = TxType.REQUIRED, rollbackOn = { RuntimeException.class })
  public void deleteManuelTransaction(int id) {

    // var tran = entityManager.getTransaction(); // custom transaction yönetimi

    try {

      var entity = findById(id); // attached

      if (entity == null) {
        throw new EntityNotFoundException("Supplier nesnesi bulunamadı");
      }

      // tran.begin();

      entityManager.remove(entity);

      // tran.commit();

    } catch (Exception e) {
      // TODO: handle exception
      // tran.rollback();
    }

    // https://www.baeldung.com/spring-data-jpa-stored-procedures

  }

  @Override
  public List<Supplier> findAll() {
    return entityManager.createQuery("Select s from Supplier s").getResultList();
  }

  @Override
  public Supplier findById(int id) {
    return entityManager.find(Supplier.class, id);
  }

  @Override
  public List<Supplier> findAllWithCriteriaApi() {
    var cb = entityManager.getCriteriaBuilder(); // kriter oluşturmak için kullandık
    var cq = cb.createQuery(Supplier.class); // supplier class bir query aç // cq criteria query
    var root = cq.from(Supplier.class);
    cq.select(root);

    // select * from supplier

    var query = entityManager.createQuery(cq);
    return query.getResultList();

  }

}
