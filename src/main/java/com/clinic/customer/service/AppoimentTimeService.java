package com.clinic.customer.service;

import com.clinic.customer.entity.AppointmentTime;
import com.clinic.customer.entity.Shift;
import com.clinic.customer.repository.AppointmentTimeReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AppoimentTimeService  {
    @Autowired private AppointmentTimeReponsitory appointmentTimeReponsitory;
    public void save(AppointmentTime appointmentTime) {
        appointmentTimeReponsitory.save(appointmentTime);
    }

    public List<AppointmentTime> listAll() {
        return (List<AppointmentTime>) appointmentTimeReponsitory.findAll();
    }

    public AppointmentTime get(Long id) {
        return appointmentTimeReponsitory.findById(id).get();
    }

    public void delete(Long id) {
        appointmentTimeReponsitory.deleteById(id);
    }
    public void bookAppointmentTime(long id){
        appointmentTimeReponsitory.bookAppointmentTime(id);
    }
    public void handle(long id){
        appointmentTimeReponsitory.handle(id);
    }
}
