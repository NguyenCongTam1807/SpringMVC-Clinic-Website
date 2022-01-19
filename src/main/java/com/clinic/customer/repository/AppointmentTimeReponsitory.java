package com.clinic.customer.repository;

import com.clinic.customer.entity.AppointmentTime;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentTimeReponsitory extends CrudRepository<AppointmentTime ,Long> {

    @Modifying
    @Query(value = "update AppointmentTime set active = 1 where id = ?1", nativeQuery = true)
    void bookAppointmentTime(long id);

    @Modifying
    @Query(value = "update AppointmentTime set active = 0 where id = ?1", nativeQuery = true)
    void handle(long id);
}
