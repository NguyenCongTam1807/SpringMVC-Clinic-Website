package com.clinic.customer.repository;

import com.clinic.customer.entity.Medicine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicineReponsitory extends CrudRepository<Medicine,Long> {

    @Query(value = "select * from Medicine where medicineType_id = ?1", nativeQuery = true)
    List<Medicine> getProductByCategoryId(long id);

    @Query(value = "SELECT * FROM Medicine where name like %?1%",nativeQuery = true)
    List<Medicine> search(String search);

    @Query(value = "select * from Medicine where medicineType_id = ?1 order by name ASC", nativeQuery = true)
    List<Medicine> sortCatNameASC(long id);

    @Query(value = "select * from Medicine where medicineType_id = ?1 order by name DESC", nativeQuery = true)
    List<Medicine> sortCatNameDESC(long id);

    @Query(value = "select * from Medicine where medicineType_id = ?1 order by price ASC", nativeQuery = true)
    List<Medicine> sortCatPriceASC(long id);

    @Query(value = "select * from Medicine where medicineType_id = ?1 order by price DESC", nativeQuery = true)
    List<Medicine> sortCatPriceDESC(long id);
}
