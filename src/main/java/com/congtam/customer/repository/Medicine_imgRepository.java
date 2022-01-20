package com.congtam.customer.repository;

import com.congtam.customer.pojos.Medicine_img;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Medicine_imgRepository extends CrudRepository<Medicine_img,Long> {

    @Query(value = "select * from Medicine_img where medicineId = ?1", nativeQuery = true)
    List<Medicine_img> getAllByProductId(long id);

    @Modifying
    @Query(value = "delete from Medicine_img where medicineId = ?1", nativeQuery = true)
    void deleteProduct_imgByProductId(long id);
}
