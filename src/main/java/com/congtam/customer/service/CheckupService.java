package com.congtam.customer.service;

import com.congtam.customer.pojos.Checkup;
import com.congtam.customer.pojos.Employee;
import com.congtam.customer.pojos.Shift;
import com.congtam.customer.repository.CheckupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CheckupService {
    @Autowired
    private CheckupRepository repo; //DI

    @Autowired private ShiftService shiftService;

    public void save(Checkup checkup) {
        repo.save(checkup);
    }

    public List<Checkup> listAll() {
        return (List<Checkup>) repo.findAll();
    }

    public Checkup get(Long id) {
        return repo.findById(id).get();
    }

    public Checkup findCheckupByUserId(Long id) {
        return repo.findCheckupByUserId(id);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Checkup findCheckupByUser(Employee employee){
        return repo.findCheckupByEmployee(employee);
    }

    public void editTotal(float total,long id){
        repo.editTotal(total,id);
    }

    public void pay(long id){
        repo.pay(id);
    }

    public void handle(long id){
        repo.handle(id);
    }

    public void saveRoom(Employee employee,long id){
        Shift shift = shiftService.get(id);
        Checkup checkup = new Checkup();
        checkup.setCheckupDate(new Date());
        checkup.setTotal(0);
        checkup.setStatus(0);
        checkup.setEmployee(employee);
        checkup.setShift(shift);
        repo.save(checkup);
    }
}
