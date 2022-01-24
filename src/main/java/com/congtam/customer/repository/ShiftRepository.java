package com.congtam.customer.repository;

import com.congtam.customer.pojos.Shift;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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

    @Query("SELECT s FROM Shift s where s.name LIKE CONCAT('%',:search,'%') and s.active = 0")
    List<Shift> search(@Param("search") String search);

}
