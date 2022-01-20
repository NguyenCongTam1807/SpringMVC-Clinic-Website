package com.congtam.customer.service;

import com.congtam.customer.pojos.Checkup;
import com.congtam.customer.pojos.CheckupDetail;
import com.congtam.customer.pojos.Employee;
import com.congtam.customer.pojos.Medicine;
import com.congtam.customer.repository.CheckupDetailRepository;
import com.congtam.customer.repository.CheckupRepository;
import com.congtam.customer.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CheckupDetailService {
    @Autowired private CheckupDetailRepository repo; //DI
    @Autowired private MedicineRepository medicineRepository;
    @Autowired private CheckupRepository checkupRepository;
    @Autowired private CheckupDetailRepository checkupDetailRepository;
    @Autowired private Medicine_TypeService medicine_typeService;
    @Autowired private CheckupService checkupService;


    public void save(CheckupDetail checkupDetail) {
        repo.save(checkupDetail);
    }

    public List<CheckupDetail> listAll() {
        return (List<CheckupDetail>) repo.findAll();
    }

    public CheckupDetail get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<CheckupDetail> findAllByCheckupDetailId(long id) {
        return (List<CheckupDetail>) repo.findAllByCheckupDetailId(id);
    }

    public Integer addProduct(long productId, int quantity, Employee employee){
        System.out.println(quantity);
        int addedQuantity = quantity;
        Medicine medicine = medicineRepository.findById(productId).get();

        Checkup checkup = checkupRepository.findCheckupByUserId(employee.getId());

        CheckupDetail checkupDetail=null;
        if (checkup != null && checkup.getStatus() == 0){
            checkupDetail = repo.findByMedicineAndCheckup(medicine,checkup);
            if (checkupDetail != null){
                addedQuantity = checkupDetail.getQuantity() + quantity;
                checkupDetail.setQuantity(addedQuantity);
                checkupDetail.setTotal(addedQuantity*checkupDetail.getUnitPrice());
            }else {
                checkupDetail = new CheckupDetail();
                checkupDetail.setCheckup(checkup);
                checkupDetail.setMedicine(medicine);
                checkupDetail.setQuantity(quantity);
                checkupDetail.setUnitPrice(medicine.getPrice());
                checkupDetail.setTotal(quantity*(medicine.getPrice()));
            }
            checkup.setCheckupDate(new Date());
            checkup.setTotal(checkup.getTotal()+checkupDetail.getUnitPrice()*quantity);
        }else {
            checkup = new Checkup();
            checkup.setEmployee(employee);
            checkup.setStatus(0);
            checkup.setCheckupDate(new Date());

            checkupDetail = new CheckupDetail();
            checkupDetail.setCheckup(checkup);
            checkupDetail.setMedicine(medicine);
            checkupDetail.setQuantity(quantity);
            checkupDetail.setUnitPrice(medicine.getPrice());
            checkupDetail.setTotal(quantity*(medicine.getPrice()));
            checkup.setTotal(checkupDetail.getTotal());
        }
        checkupService.save(checkup);
        save(checkupDetail);
        return addedQuantity;
    }
}


