package com.clinic.customer.controller;

import com.clinic.customer.service.EmployeeService;
import com.clinic.customer.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired private EmployeeService employeeService;

    @RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
    public String register(HttpSession session, ModelMap model,
                           @RequestParam(value = "error", required = false) String error) {
        session.setAttribute("employee", null);
        try {
            if (error.equals("true")) {
                model.put("error", "Tên đăng nhập hoặc mật khẩu không đúng !!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "register";
    }

    @RequestMapping(value = "/dang-ky", method = RequestMethod.POST)
    public String registerpost(@RequestParam("name")String name,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password) {
        employeeService.register(email, StringUtil.md5(password),name);
        return "redirect:/dang-nhap";
    }

}
