package com.congtam.customer.controller;

import com.congtam.customer.pojos.Employee;
import com.congtam.customer.service.EmployeeService;
import com.congtam.customer.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller(value = "loginControllerOfAdmin")
public class LoginController {

    @Autowired private EmployeeService employeeService;

    @RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
    public String login(HttpSession session, ModelMap model,
                        @RequestParam(value = "error", required = false) String error) {
        Employee employee = (Employee) session.getAttribute("employee");
        if(employee != null){
            return "redirect:/trang-chu";
        }else {
            session.setAttribute("employee", null);
            try {
                if (error.equals("true")) {
                    model.put("error", "Tên đăng nhập hoặc mật khẩu không đúng !!");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return "login";
        }
    }

    @RequestMapping(value = "/dang-nhap", method = RequestMethod.POST)
    public String login(HttpSession session, ModelMap model, @RequestParam("email") String email,
                        @RequestParam("password") String password) {
        if (email.equals("") || password.equals("")) {
            return "redirect:/dang-ky?error=true";
        }

        Employee employee;
        employee = employeeService.login(email, StringUtil.md5(password));
        session.setAttribute("employee", employee);

        if (employee == null) {
            return "redirect:/dang-nhap?error=true";
        }
        else
            return "redirect:/trang-chu";
//        if (employee.getUserType() == 0 || employee.getUserType() == 1) {
//            return "redirect:/trang-chu";
//        }
//        return "redirect:/dang-nhap?error=true";
    }


    @RequestMapping(value = "dang-xuat", method = RequestMethod.GET)
    public String logout(HttpSession session, ModelMap model,
                         @RequestParam(value = "error", required = false) String error) {
        session.removeAttribute("employee");
        return "redirect:/trang-chu";
    }

    @ModelAttribute
    public void commonAttrs(Model model, HttpSession session) {
        model.addAttribute("employee", session.getAttribute("employee"));
    }
}
