package com.congtam.customer.service;

import com.congtam.customer.pojos.Shift;
import com.congtam.customer.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShiftService {
    @Autowired
    private ShiftRepository repo;

    public void save(Shift shift) {
        repo.save(shift);
    }

    public List<Shift> listAll() {
        return (List<Shift>) repo.findAll();
    }

    public Shift get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void bookRoom(long id){
        repo.bookRoom(id);
    }
    public void handle(long id){
        repo.handle(id);
    }
    public void insertShift(String name, String email,String note,String phone){
        repo.insertShift(name,email,note,phone);
    }

    public List<Shift> search(String search){
        return repo.search(search);
    }
}
