package com.clinic.customer.repository;

import com.clinic.customer.entity.Checkup;
import com.clinic.customer.entity.CheckupDetail;
import com.clinic.customer.entity.Medicine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CheckupDetailReponsitory extends CrudRepository<CheckupDetail,Long> {
    public CheckupDetail findByMedicineAndCheckup(Medicine medicine, Checkup checkup);

    @Query(value = "select * from CheckupDetail where checkupId = ?1", nativeQuery = true)
    public List<CheckupDetail> findAllByOderDetailId(long id);

}
