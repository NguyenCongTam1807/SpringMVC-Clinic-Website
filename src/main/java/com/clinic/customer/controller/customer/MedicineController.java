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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MedicineController {

    @Autowired private Medicine_TypeService categoryService;
    @Autowired private MedicineService productService;
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

//    @GetMapping(value = {"/danh-muc/{id}","/danh-muc"})
//    public ModelAndView home(@PathVariable(name = "id",required = false) Long id,HttpSession session){
//        if (id == null){
//            id = 1l;
//        }
//        ModelAndView mav = new ModelAndView("public/c-product");
//        mav.addObject("name","Danh mục");
//        mav.addObject("listById",productService.getProductByCategoryId(id));
//        return mav;
//    }


    @GetMapping(value = {"/danh-muc/{id}","/danh-muc"})
    public ModelAndView categorysort(@PathVariable(name = "id",required = false) Long id,
                                     @RequestParam(value = "sort",required = false)String sort){
        if (id == null){
            id = 1l;
        }
        if (sort==null){
            sort="az";
        }
        ModelAndView mav = new ModelAndView("public/c-product");
        if (sort.equals("low")){
            mav.addObject("listById",productService.sortCatPriceASC(id));
        }else if(sort.equals("high")){
            mav.addObject("listById",productService.sortCatPriceDESC(id));
        }else if(sort.equals("za")){
            mav.addObject("listById",productService.sortCatNameDESC(id));
        }else {
            mav.addObject("listById",productService.sortCatNameASC(id));
        }
        mav.addObject("name","Thuốc");
        return mav;
    }

}
