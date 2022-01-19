package com.clinic.customer.controller.admin;


import com.clinic.customer.entity.Employee;
import com.clinic.customer.entity.Shift;
import com.clinic.customer.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller(value = "roomControllerOfAdmin")
public class ShiftController {
    @Autowired
    private ShiftService shiftService;

    @RequestMapping("/manager/shift")
    public String home(HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("employee");
        ModelAndView mav = null;
        if (employee!=null && employee.getUserType()==0){
            List<Shift> listShift = shiftService.listAll();
            model.addAttribute("listShift", listShift);
        }else {
            return "redirect:/dang-nhap";
        }
        return "admin/shift/index";
    }
}
