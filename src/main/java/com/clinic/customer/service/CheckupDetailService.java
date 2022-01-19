package com.clinic.customer.service;

import com.clinic.customer.entity.Checkup;
import com.clinic.customer.entity.CheckupDetail;
import com.clinic.customer.entity.Employee;
import com.clinic.customer.entity.Medicine;
import com.clinic.customer.repository.CheckupDetailReponsitory;
import com.clinic.customer.repository.CheckupReponsitory;
import com.clinic.customer.repository.MedicineReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CheckupDetailService {
    @Autowired private CheckupDetailReponsitory repo; //DI
    @Autowired private MedicineReponsitory medicineReponsitory;
    @Autowired private CheckupReponsitory checkupReponsitory;
    @Autowired private CheckupDetailReponsitory checkupDetailReponsitory;
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

    public List<CheckupDetail> findAllByOderDetailId(long id) {
        return (List<CheckupDetail>) repo.findAllByOderDetailId(id);
    }

    public Integer addProduct(long productId, int quantity, Employee employee){
        System.out.println(quantity);
        int addedQuantity = quantity;
        Medicine medicine = medicineReponsitory.findById(productId).get();

        Checkup checkup = checkupReponsitory.findOderByUserId(employee.getId());

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


