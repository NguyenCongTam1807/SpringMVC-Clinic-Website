package com.congtam.customer.repository;

import com.congtam.customer.pojos.Checkup;
import com.congtam.customer.pojos.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CheckupRepository extends CrudRepository<Checkup,Long> {
    @Query(value = "select * from Checkup where employeeId = ?1 and status = 0", nativeQuery = true)
    Checkup findCheckupByUserId(long id);

    public Checkup findCheckupByEmployee(Employee employee);

    @Modifying
    @Query(value = "update Checkup set total = ?1 where id = ?2", nativeQuery = true)
    void editTotal(float total,long id);

    @Modifying
    @Query(value = "update Checkup set status = 1 where id = ?1", nativeQuery = true)
    void pay(long id);

    @Modifying
    @Query(value = "update Checkup set status = 2 where id = ?1 and status = 1", nativeQuery = true)
    void handle(long id);

}
