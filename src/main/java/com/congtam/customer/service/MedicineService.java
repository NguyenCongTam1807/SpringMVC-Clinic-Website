package com.congtam.customer.service;

import com.congtam.customer.pojos.Medicine;
import com.congtam.customer.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MedicineService {
    @Autowired
    private MedicineRepository repo;
    public void save(Medicine medicine) {
        repo.save(medicine);
    }

    public List<Medicine> listAll() {
        return (List<Medicine>) repo.findAll();
    }

    public Medicine get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Medicine> getProductByCategoryId(long id){
        return repo.getProductByCategoryId(id);
    }

    public List<Medicine> search(String search){
        return (List<Medicine>) repo.search(search);
    }

    public List<Medicine> sortCatNameASC(long id){
        return repo.sortCatNameASC(id);
    }
    public List<Medicine> sortCatNameDESC(long id){
        return repo.sortCatNameDESC(id);
    }
    public List<Medicine> sortCatPriceASC(long id){
        return repo.sortCatPriceASC(id);
    }
    public List<Medicine> sortCatPriceDESC(long id){
        return repo.sortCatPriceDESC(id);
    }

}
