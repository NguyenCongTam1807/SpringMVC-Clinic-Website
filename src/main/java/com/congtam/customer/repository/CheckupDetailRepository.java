package com.congtam.customer.repository;

import com.congtam.customer.pojos.Checkup;
import com.congtam.customer.pojos.CheckupDetail;
import com.congtam.customer.pojos.Medicine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CheckupDetailRepository extends CrudRepository<CheckupDetail,Long> {
    public CheckupDetail findByMedicineAndCheckup(Medicine medicine, Checkup checkup);

    @Query(value = "select * from CheckupDetail where checkupId = ?1", nativeQuery = true)
    public List<CheckupDetail> findAllByCheckupDetailId(long id);

}
