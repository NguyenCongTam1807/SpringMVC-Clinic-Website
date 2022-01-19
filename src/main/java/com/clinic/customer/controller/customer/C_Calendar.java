package com.clinic.customer.controller.customer;

import com.clinic.customer.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class C_Calendar {

    @Autowired private ShiftService shiftService;

    @RequestMapping(value = "/huy-lich")
    public String search(){
        return "public/cancelCalendar";
    }

    @RequestMapping(value = "/huy-lich",method = RequestMethod.POST)
    public String table(@RequestParam String search, Model model){
        model.addAttribute("listShift",shiftService.search(search));
        return "public/table";
    }
    @RequestMapping(value = "/huy-lich/del")
    public String del(@RequestParam long id, RedirectAttributes re){
        shiftService.delete(id);
        re.addFlashAttribute("msg","Hủy lịch thành công");
        return "redirect:/huy-lich";
    }

    @RequestMapping(value = "/abc")
    public String seasrch(){
        return "public/table";
    }
}
