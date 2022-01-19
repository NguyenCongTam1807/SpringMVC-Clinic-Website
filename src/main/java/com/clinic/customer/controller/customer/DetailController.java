package com.clinic.customer.controller.customer;

import com.clinic.customer.entity.Checkup;
import com.clinic.customer.entity.CheckupDetail;
import com.clinic.customer.entity.Employee;
import com.clinic.customer.service.CheckupDetailService;
import com.clinic.customer.service.CheckupService;
import com.clinic.customer.service.MedicineService;
import com.clinic.customer.service.Medicine_TypeService;
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
    @Autowired private CheckupService oderService;
    @Autowired private CheckupDetailService oderDetailService;

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

    @GetMapping("/san-pham/{id}")
    public ModelAndView home(@PathVariable(name = "id") Long id, HttpSession session){
        ModelAndView mav = new ModelAndView("public/detail-product");
//
//        User user = (User) session.getAttribute("user");
//        List<OderDetail> list = new ArrayList<>();
//        if (user!=null){
//            Oder oder = oderService.findOderByUser(user);
//            list = oderDetailService.findAllByOderDetailId(oder.getId());
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
