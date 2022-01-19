package com.clinic.customer.controller.customer;

import com.clinic.customer.entity.Checkup;
import com.clinic.customer.entity.CheckupDetail;
import com.clinic.customer.entity.Employee;
import com.clinic.customer.service.*;
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
    @Autowired private CheckupService oderService;
    @Autowired private CheckupDetailService oderDetailService;
    @Autowired private ShiftService roomService;

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

    @GetMapping("/benh-nhan")
    public ModelAndView home(HttpSession session){
        ModelAndView mav = new ModelAndView("public/bookShift");
        mav.addObject("listRoom",roomService.listAll());
        mav.addObject("listProduct",productService.listAll());
        return mav;
    }

    @PostMapping("/benh-nhan")
    public String phong(@RequestParam(value = "id") long id, HttpSession session,
                        RedirectAttributes re){
        Employee employee = (Employee) session.getAttribute("employee");
        Checkup oder = new Checkup();
        if (employee != null){
            oder = oderService.findOderByUserId(employee.getId());
            if (oder != null){
                re.addFlashAttribute("roomActive","Chưa kê xong toa trong đơn");
                return "redirect:/benh-nhan";
            }
            roomService.bookRoom(id);
            oderService.saveRoom(employee,id);
            return "redirect:/danh-muc";
        }else {
            return "redirect:/dang-nhap";
        }
    }

}
