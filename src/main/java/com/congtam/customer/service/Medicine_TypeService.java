package com.congtam.customer.service;

import com.congtam.customer.pojos.Medicine_type;
import com.congtam.customer.repository.MedicineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Medicine_TypeService{
    @Autowired private MedicineTypeRepository repo; //DI
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

    public List<Object[]> medsByCateStats(){return repo.medsByCateStats();};

}
