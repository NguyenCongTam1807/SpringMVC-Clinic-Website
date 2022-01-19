package com.clinic.customer.controller.admin;

import com.clinic.customer.constant.MessageConstant;
import com.clinic.customer.entity.Employee;
import com.clinic.customer.service.EmployeeService;
import com.clinic.customer.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller(value = "userControllerOfAdmin")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/manager/employee")
    public String home(HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("employee");
        ModelAndView mav = null;
        if (employee!=null && employee.getUserType()==0){
            List<Employee> listEmployee = employeeService.listAll();
            model.addAttribute("listEmployee", listEmployee);
        }else {
            return "redirect:/dang-nhap";
        }
        return "admin/employee/index";
    }

    @RequestMapping("/manager/employee/new")
    public String newAccountForm(Map<String, Object> model, HttpServletRequest request) {
        Employee employee = new Employee();
        model.put("employee", employee);
        return "admin/employee/add";
    }

    @RequestMapping(value = "/manager/employee/new", method = RequestMethod.POST)
    public String saveAccount(@Valid @ModelAttribute("employee") Employee employee, BindingResult rs, @RequestParam("password")String pass
            , HttpServletResponse response, RedirectAttributes rss) {
        if(rs.hasErrors()) {
            return "admin/employee/add";
        }
        employee.setPassword(StringUtil.md5(pass));
        response.setCharacterEncoding("utf-8");
        employeeService.save(employee);
        rss.addFlashAttribute("msg", MessageConstant.ADD_SUSSCESS);
        return "redirect:/manager/employee";
    }

    @RequestMapping(value = "/manager/employee/edit", method = RequestMethod.POST)
    public String editAccount(@ModelAttribute("account") Employee employee,@RequestParam("password")String pass
            , HttpServletResponse response,RedirectAttributes rs) {
        employee.setPassword(StringUtil.md5(pass));
        response.setCharacterEncoding("utf-8");
        employeeService.save(employee);
        rs.addFlashAttribute("msg",MessageConstant.EDIT_SUSSCESS);
        return "redirect:/manager/employee";
    }

    @RequestMapping("/manager/employee/edit")
    public ModelAndView editCustomerForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("admin/employee/edit");
        Employee employee = employeeService.get(id);
        mav.addObject("password",employee.getPassword());
        mav.addObject("employee", employee);

        return mav;
    }

    @RequestMapping("/manager/employee/delete")
    public String deleteAccountForm(@RequestParam long id, RedirectAttributes rs) {
        try{
            employeeService.delete(id);
            rs.addFlashAttribute("msg", MessageConstant.DELETE_SUSSCESS);
            return "redirect:/manager/employee";
        }catch (Exception e){
            rs.addFlashAttribute("msg_err", MessageConstant.DELETE_SUSSCESS);
            return "redirect:/manager/employee";
        }
    }
}
