package com.clinic.customer.utils;

import com.clinic.customer.entity.Checkup;
import com.clinic.customer.entity.CheckupDetail;
import com.clinic.customer.entity.Employee;
import com.clinic.customer.service.CheckupDetailService;
import com.clinic.customer.service.CheckupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    @Autowired
    private CheckupService checkupService;
    @Autowired private CheckupDetailService checkupDetailService;

    public List<CheckupDetail> showCart(Employee employee){
        List<CheckupDetail> list = new ArrayList<>();
        Checkup oder = checkupService.findOderByUser(employee);
        return checkupDetailService.findAllByOderDetailId(oder.getId());
    }
}
