package com.congtam.customer.controller.customer;

import com.congtam.customer.pojos.Checkup;
import com.congtam.customer.pojos.CheckupDetail;
import com.congtam.customer.pojos.Employee;
import com.congtam.customer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShiftController {
    @Autowired
    private MedicineService productService;
    @Autowired private Medicine_TypeService categoryService;
    @Autowired private CheckupService checkupService;
    @Autowired private CheckupDetailService checkupDetailService;
    @Autowired private ShiftService shiftService;

    @ModelAttribute
    public void modelAtr(Model model, HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        List<CheckupDetail> list = new ArrayList<>();
        Checkup checkup;
        if (employee!=null){
            checkup = checkupService.findCheckupByUserId(employee.getId());
            if ( checkup!= null && checkup.getStatus()==0){
                list = checkupDetailService.findAllByCheckupDetailId(checkup.getId());
                model.addAttribute("checkup",checkup);
            }
        }else {
            list=null;
        }
        model.addAttribute("listCart",list);
        model.addAttribute("listCategory", categoryService.listAll());
    }

    @GetMapping("/benh-nhan")
    public ModelAndView home(HttpSession session){
        ModelAndView mav = new ModelAndView("public/bookShift");
        mav.addObject("listShift", shiftService.listAll());
        mav.addObject("listProduct",productService.listAll());
        return mav;
    }

    @PostMapping("/benh-nhan")
    public String phong(@RequestParam(value = "id") long id, HttpSession session,
                        RedirectAttributes re){
        Employee employee = (Employee) session.getAttribute("employee");
        Checkup checkup;
        if (employee != null){
            checkup = checkupService.findCheckupByUserId(employee.getId());
            if (checkup != null){
                re.addFlashAttribute("patientActive","Chưa kê xong toa trong đơn");
                return "redirect:/benh-nhan";
            }
            shiftService.bookRoom(id);
            checkupService.saveRoom(employee,id);
            return "redirect:/danh-muc";
        }else {
            return "redirect:/dang-nhap";
        }
    }

}
