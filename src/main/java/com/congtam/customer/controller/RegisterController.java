package com.congtam.customer.controller;

import com.congtam.customer.service.EmployeeService;
import com.congtam.customer.utils.StringUtil;
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
                        @RequestParam("password") String password,
                       @RequestParam("address") String address,
                       @RequestParam("phone") String phoneNumber) {
        employeeService.register(email, StringUtil.md5(password),name, address, phoneNumber);
        return "redirect:/dang-nhap";
    }

}
