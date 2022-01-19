package com.clinic.customer.controller.customer;

import com.clinic.customer.entity.Checkup;
import com.clinic.customer.entity.CheckupDetail;
import com.clinic.customer.entity.Employee;
import com.clinic.customer.service.*;
import com.clinic.customer.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired private Medicine_TypeService medicine_typeService;
    @Autowired private CheckupService checkupService;
    @Autowired private CheckupDetailService checkupDetailService;
    @Autowired private MedicineService medicineService;
    @Autowired private EmployeeService employeeService;
    @ModelAttribute
    public void modelAtr(Model model, HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        List<CheckupDetail> list = new ArrayList<>();
        Checkup oder = new Checkup();
        if (employee!=null){
            oder = checkupService.findOderByUserId(employee.getId());
            if ( oder!= null && oder.getStatus()==0){
                list = checkupDetailService.findAllByOderDetailId(oder.getId());
                model.addAttribute("oder",oder);
            }
        }else {
            list=null;
        }
        model.addAttribute("listCart",list);
        model.addAttribute("listCategory", medicine_typeService.listAll());
    }


    @GetMapping("/trang-chu")
    public ModelAndView home(HttpSession session){
        ModelAndView mav = new ModelAndView("public/index");
        mav.addObject("listMedicine",medicine_typeService.listAll());
        return mav;
    }

    @GetMapping("/thong-tin")
    public ModelAndView info(){
        ModelAndView mav = new ModelAndView("public/info");
        return mav;
    }

    @RequestMapping(value = "/thong-tin" ,method = RequestMethod.POST)
    public String infom(@RequestParam("oldpass") String oldpass,
                        @RequestParam("password")String password,
                        @RequestParam("repassword")String repassword,
                        HttpSession session,
                        RedirectAttributes re){
        Employee employee = (Employee) session.getAttribute("employee");
        if (new StringUtil().md5(oldpass).equals(employee.getPassword())){
            if (password.equals(repassword)){
                employeeService.editPass(new StringUtil().md5(password),employee.getId());
                re.addFlashAttribute("msg","Sửa mật khẩu thành công");
            }else {
                re.addFlashAttribute("err","Mật khẩu không trùng nhau");
            }
        }else {
            re.addFlashAttribute("err","Mật khẩu cũ không chính xác");
        }
        return "redirect:/thong-tin";
    }
}
