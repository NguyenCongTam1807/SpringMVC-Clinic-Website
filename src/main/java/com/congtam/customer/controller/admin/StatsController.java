package com.congtam.customer.controller.admin;

import com.congtam.customer.pojos.Employee;
import com.congtam.customer.service.CheckupService;
import com.congtam.customer.service.Medicine_TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller(value = "statsControllerOfAdmin")
public class StatsController {
    @Autowired
    private Medicine_TypeService medicineTypeService;
    @Autowired
    private CheckupService checkupService;

//    @GetMapping("/manager/stats")
//    public String cateStats(HttpSession session, Model model) {
//        Employee employee = (Employee) session.getAttribute("employee");
//        if (employee!=null && employee.getUserType()==0){
//            List<Object[]> medsByCate = medicineTypeService.medsByCateStats();
//            model.addAttribute("medsByCate", medsByCate);
//        }else {
//            return "redirect:/dang-nhap";
//        }
//        return "admin/stats/index";
//    }

    @GetMapping("/manager/stats")
    public String revenueByMonth(HttpSession session, Model model, @RequestParam(defaultValue = "2022") int year) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee!=null && employee.getUserType()==0){
            List<Object[]> medsByCate = medicineTypeService.medsByCateStats();
            model.addAttribute("medsByCate", medsByCate);

            List<Float> revenueByMonth = new ArrayList<>();
            for(int i=1;i<=12;i++)
                revenueByMonth.add(checkupService.revenueByMonth(year,i));
            model.addAttribute("revenueByMonth", revenueByMonth);
        }else {
            return "redirect:/dang-nhap";
        }
        return "admin/stats/index";
    }

}
