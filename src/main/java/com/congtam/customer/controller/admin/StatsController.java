package com.congtam.customer.controller.admin;

import com.congtam.customer.pojos.Employee;
import com.congtam.customer.service.Medicine_TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller(value = "statsControllerOfAdmin")
public class StatsController {
    @Autowired
    private Medicine_TypeService medicineTypeService;

    @RequestMapping("/manager/stats")
    public String home(HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee!=null && employee.getUserType()==0){
            List<Object[]> medsByCate = medicineTypeService.medsByCateStats();
            model.addAttribute("medsByCate", medsByCate);
        }else {
            return "redirect:/dang-nhap";
        }
        return "admin/stats/index";
    }

}
