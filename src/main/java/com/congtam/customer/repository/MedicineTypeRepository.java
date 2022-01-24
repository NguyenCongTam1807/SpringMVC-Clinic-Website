package com.congtam.customer.repository;

import com.congtam.customer.pojos.Medicine_type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicineTypeRepository extends CrudRepository<Medicine_type, Long> {
        @Query(value = "SELECT t.id, t.name,count(m.id) FROM medicine m RIGHT JOIN medicine_type t" +
            "            ON m.medicineType_id = t.id" +
            "            GROUP BY t.id",
            nativeQuery = true)
        List<Object[]> medsByCateStats();
}
