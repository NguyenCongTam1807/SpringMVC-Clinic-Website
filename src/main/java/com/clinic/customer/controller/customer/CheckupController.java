package com.clinic.customer.controller.customer;

import com.clinic.customer.entity.Checkup;
import com.clinic.customer.entity.CheckupDetail;
import com.clinic.customer.entity.Employee;
import com.clinic.customer.service.CheckupDetailService;
import com.clinic.customer.service.CheckupService;
import com.clinic.customer.service.Medicine_TypeService;
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
    @Autowired private CheckupService oderService;
    @Autowired private CheckupDetailService oderDetailService;
    @Autowired private Medicine_TypeService categoryService;

    @ModelAttribute
    public void modelAtr(Model model, HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        List<CheckupDetail> list = new ArrayList<>();
        Checkup oder = new Checkup();
        if (employee!=null){
            oder = oderService.findOderByUserId(employee.getId());
            if ( oder!= null && oder.getStatus()==0){
                list = oderDetailService.findAllByOderDetailId(oder.getId());
                model.addAttribute("oder",oder);
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
            oderDetailService.addProduct(id,quantity,employee);
            return "redirect:/dang-nhap";
        }else {
            return null;
        }
    }
    @GetMapping("/add-cart")
    public String addCart1(HttpSession session,@RequestParam("id")long id,@RequestParam("quantity") int quantity){
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee!=null){
            oderDetailService.addProduct(id,quantity,employee);
            return "redirect:/gio-hang";
        }else {
            return "redirect:/dang-nhap";
        }
    }

}
