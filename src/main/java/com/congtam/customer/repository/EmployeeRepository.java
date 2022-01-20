package com.congtam.customer.repository;

import com.congtam.customer.pojos.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    @Query(value = "select * from Employee where email = ?1 and password = ?2", nativeQuery = true)
    Employee login(String email, String password);

    @Modifying
    @Query(value = "INSERT INTO Employee (email, password, userName,userType) VALUES (?1,?2,?3,2)", nativeQuery = true)
    void register(String email, String password,String name);

    @Modifying
    @Query(value = "update Employee set password = ?1 where id = ?2", nativeQuery = true)
    void editPass(String pass , long id);
}
