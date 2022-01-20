package com.congtam.customer.controller.customer;

import com.congtam.customer.pojos.Checkup;
import com.congtam.customer.pojos.CheckupDetail;
import com.congtam.customer.pojos.Employee;
import com.congtam.customer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private CheckupService checkupService;
    @Autowired private CheckupDetailService checkupDetailService;
    @Autowired private Medicine_TypeService categoryService;
    @Autowired private AppoimentTimeService appoimentTimeService;
    @Autowired private ShiftService shiftService;

    @ModelAttribute
    public void modelAtr(Model model, HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        List<CheckupDetail> list = new ArrayList<>();
        Checkup checkup = new Checkup();
        if (employee!=null){
            checkup = checkupService.findCheckupByUserId(employee.getId());
            if ( checkup!= null && checkup.getStatus()==0){
                list = checkupDetailService.findAllByCheckupDetailId(checkup.getId());
                model.addAttribute("checkup",checkup);
            }
        }else {
            list=null;
        }
        model.addAttribute("listatime",appoimentTimeService.listAll());
        model.addAttribute("listCart",list);
        model.addAttribute("listCategory", categoryService.listAll());
    }

    @RequestMapping(value = "/dat-lich")
    public String loadBook(){
        return "public/bookAppointment";
    }

    @RequestMapping(value = "/dat-lich",method = RequestMethod.POST)
    public String appointmentTime(@RequestParam String name,@RequestParam String email,@RequestParam String phone
            ,@RequestParam int time,@RequestParam String message){
        shiftService.insertShift(name,email,message,phone);
                appoimentTimeService.bookAppointmentTime(time);
        return "public/success";
    }
}
