package com.clinic.customer.repository;

import com.clinic.customer.entity.Shift;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShiftRepository extends CrudRepository<Shift, Long> {
    @Modifying
    @Query(value = "update Shift set active = 1 where id = ?1", nativeQuery = true)
    void bookRoom(long id);

    @Modifying
    @Query(value = "update Shift set active = 3 where id = ?1", nativeQuery = true)
    void handle(long id);

    @Modifying
    @Query(value = "INSERT INTO Shift (active, name, email,note,phone) VALUES (0,?1,?2,?3,?4)", nativeQuery = true)
    void insertShift(String name, String email,String note,String phone);

    @Query(value = "SELECT * FROM Shift where name = ?1 and active = 0",nativeQuery = true)
    List<Shift> search(String search);
}
