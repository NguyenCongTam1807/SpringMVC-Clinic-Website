package com.clinic.customer.controller.admin;
import com.clinic.customer.constant.MessageConstant;
import com.clinic.customer.entity.Checkup;
import com.clinic.customer.entity.Employee;
import com.clinic.customer.service.CheckupService;
import com.clinic.customer.service.ShiftService;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller(value = "oderControllerOfAdmin")
public class CheckupController {
    @Autowired private CheckupService checkupService;
    @Autowired private ShiftService shiftService;

    @RequestMapping("/manager/checkup")
    public String loadOder(HttpSession session, Model model){
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee!=null && employee.getUserType()==0){
            List<Checkup> checkups = checkupService.listAll();
            model.addAttribute("listCheckup", checkups);
        }else {
            return "redirect:/dang-nhap";
        }
        return "admin/checkup/index";
    }

    @RequestMapping("/manager/checkup/handle")
    public String handle(@RequestParam long id,@RequestParam long idshift, RedirectAttributes re){
        Checkup checkup = checkupService.get(id);
        if (checkup.getStatus()!=0){
            checkupService.handle(id);
            shiftService.handle(idshift);
            re.addFlashAttribute("msg", MessageConstant.HANDLE);
        }else {
            re.addFlashAttribute("msg_err", MessageConstant.HANDLE_ERR);
        }
        return "redirect:/manager/checkup";
    }
}
