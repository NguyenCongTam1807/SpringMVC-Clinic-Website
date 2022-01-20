package com.congtam.customer.utils;

import com.congtam.customer.pojos.Checkup;
import com.congtam.customer.pojos.CheckupDetail;
import com.congtam.customer.pojos.Employee;
import com.congtam.customer.service.CheckupDetailService;
import com.congtam.customer.service.CheckupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    @Autowired
    private CheckupService checkupService;
    @Autowired private CheckupDetailService checkupDetailService;

    public List<CheckupDetail> showCart(Employee employee){
        List<CheckupDetail> list = new ArrayList<>();
        Checkup order = checkupService.findCheckupByUser(employee);
        return checkupDetailService.findAllByCheckupDetailId(order.getId());
    }
}
