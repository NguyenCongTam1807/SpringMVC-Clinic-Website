package com.congtam.customer.service;

import com.congtam.customer.pojos.Medicine_img;
import com.congtam.customer.repository.Medicine_imgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Medicine_imgService {
    @Autowired private Medicine_imgRepository repo;
    public void save(Medicine_img medicine_img) {
        repo.save(medicine_img);
    }

    public List<Medicine_img> getAllByProductId(long id) {
        return (List<Medicine_img>) repo.getAllByProductId(id);
    }

    public void deleteProduct_imgByProductId(Long id) {
        repo.deleteProduct_imgByProductId(id);
    }
}
