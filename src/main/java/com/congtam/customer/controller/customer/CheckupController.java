package com.congtam.customer.controller.customer;

import com.congtam.customer.pojos.Checkup;
import com.congtam.customer.pojos.CheckupDetail;
import com.congtam.customer.pojos.Employee;
import com.congtam.customer.service.CheckupDetailService;
import com.congtam.customer.service.CheckupService;
import com.congtam.customer.service.Medicine_TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CheckupController {
    @Autowired private CheckupService checkupService;
    @Autowired private CheckupDetailService orderDetailService;
    @Autowired private Medicine_TypeService categoryService;

    @ModelAttribute
    public void modelAtr(Model model, HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        List<CheckupDetail> list = new ArrayList<>();
        Checkup order = new Checkup();
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

    @PostMapping("/add-cart")
    public String addCart(HttpSession session,@RequestParam("id")long id,@RequestParam("quantity") int quantity){
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee!=null){
            orderDetailService.addProduct(id,quantity,employee);
            return "redirect:/dang-nhap";
        }else {
            return null;
        }
    }
    @GetMapping("/add-cart")
    public String addCart1(HttpSession session,@RequestParam("id")long id,@RequestParam("quantity") int quantity){
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee!=null){
            orderDetailService.addProduct(id,quantity,employee);
            return "redirect:/gio-hang";
        }else {
            return "redirect:/dang-nhap";
        }
    }

}
