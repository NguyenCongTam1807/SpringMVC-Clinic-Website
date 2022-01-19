package com.clinic.customer.service;

import com.clinic.customer.entity.Medicine_type;
import com.clinic.customer.repository.MedicineTypeReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Medicine_TypeService {
    @Autowired private MedicineTypeReponsitory repo; //DI
    public void save(Medicine_type medicine_type) {
        repo.save(medicine_type);
    }

    public List<Medicine_type> listAll() {
        return (List<Medicine_type>) repo.findAll();
    }

    public Medicine_type get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
