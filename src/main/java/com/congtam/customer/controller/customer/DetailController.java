package com.congtam.customer.controller.customer;

import com.congtam.customer.pojos.Checkup;
import com.congtam.customer.pojos.CheckupDetail;
import com.congtam.customer.pojos.Employee;
import com.congtam.customer.service.CheckupDetailService;
import com.congtam.customer.service.CheckupService;
import com.congtam.customer.service.MedicineService;
import com.congtam.customer.service.Medicine_TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DetailController {
    @Autowired private MedicineService productService;
    @Autowired private Medicine_TypeService categoryService;
    @Autowired private CheckupService checkupService;
    @Autowired private CheckupDetailService orderDetailService;

    @ModelAttribute
    public void modelAtr(Model model, HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        List<CheckupDetail> list = new ArrayList<>();
        Checkup order;
        if (employee!=null){
            order = checkupService.findCheckupByUserId(employee.getId());
            if ( order!= null && order.getStatus()==0){
                list = orderDetailService.findAllByCheckupDetailId(order.getId());
                model.addAttribute("order",order);
            }
        }else {
            list=null;
        }
        model.addAttribute("listCart",list);
        model.addAttribute("listCategory", categoryService.listAll());
    }

    @GetMapping("/san-pham/{id}")
    public ModelAndView home(@PathVariable(name = "id") Long id, HttpSession session){
        ModelAndView mav = new ModelAndView("public/detail-product");
//
//        User user = (User) session.getAttribute("user");
//        List<orderDetail> list = new ArrayList<>();
//        if (user!=null){
//            order order = checkupService.findCheckupByUser(user);
//            list = orderDetailService.findAllByCheckupDetailId(order.getId());
//        }else {
//            list=null;
//        }
//        mav.addObject("listCart",list);
//
//        mav.addObject("listCategory", categoryService.listAll());
//        mav.addObject("listBrand",brandService.listAll());
        mav.addObject("product",productService.get(id));
        return mav;
    }
}
