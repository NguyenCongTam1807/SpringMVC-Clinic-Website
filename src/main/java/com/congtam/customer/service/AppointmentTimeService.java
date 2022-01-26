package com.congtam.customer.service;

import com.congtam.customer.pojos.AppointmentTime;
import com.congtam.customer.repository.AppointmentTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AppointmentTimeService {
    @Autowired private AppointmentTimeRepository appointmentTimeRepository;
    public void save(AppointmentTime appointmentTime) {
        appointmentTimeRepository.save(appointmentTime);
    }

    public List<AppointmentTime> listAll() {
        return (List<AppointmentTime>) appointmentTimeRepository.findAll();
    }

    public AppointmentTime get(Long id) {
        return appointmentTimeRepository.findById(id).get();
    }

    public void delete(Long id) {
        appointmentTimeRepository.deleteById(id);
    }
    public void bookAppointmentTime(long id){
        appointmentTimeRepository.bookAppointmentTime(id);
    }
    public void handle(long id){
        appointmentTimeRepository.handle(id);
    }
}
