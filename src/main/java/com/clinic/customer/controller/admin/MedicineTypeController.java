package com.clinic.customer.controller.admin;

import com.clinic.customer.constant.MessageConstant;
import com.clinic.customer.entity.Employee;
import com.clinic.customer.entity.Medicine_type;
import com.clinic.customer.service.Medicine_TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller(value = "categoryControllerOfAdmin")
public class MedicineTypeController {
    @Autowired
    private Medicine_TypeService medicine_typeService;

        @RequestMapping("/manager/medicinetype")
    public String home(HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("employee"); //nul
        if (employee != null && employee.getUserType() == 0){
            List<Medicine_type> listMedicineType = medicine_typeService.listAll();
            model.addAttribute("listMedicineType", listMedicineType);
        }else {
            return "redirect:/dang-nhap";
        }
        return "admin/medicinetype/index";
    }

    @RequestMapping("/manager/medicinetype/new")
    public String newCategoryForm(Map<String, Object> model,HttpServletRequest request) {
        Medicine_type medicine_type = new Medicine_type();
        model.put("medicinetype", medicine_type);
        return "admin/medicinetype/add";
    }

    @RequestMapping(value = "/manager/medicinetype/new", method = RequestMethod.POST)
    public String saveAccount(@ModelAttribute("medicinetype") Medicine_type medicinetype,
                              HttpServletResponse response, RedirectAttributes re) {
        response.setCharacterEncoding("utf-8");
        re.addFlashAttribute("msg", MessageConstant.ADD_SUSSCESS);
                medicine_typeService.save(medicinetype);
        return "redirect:/manager/medicinetype";
    }

    @RequestMapping(value = "/manager/medicinetype/edit", method = RequestMethod.POST)
    public String editAccount(@ModelAttribute("medicinetype") Medicine_type medicinetype, HttpServletResponse response, RedirectAttributes re) {
        response.setCharacterEncoding("utf-8");
        re.addFlashAttribute("msg", MessageConstant.EDIT_SUSSCESS);
        medicine_typeService.save(medicinetype);
        return "redirect:/manager/medicinetype";
    }

    @RequestMapping("/manager/medicinetype/edit")
    public ModelAndView editCategoryForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("admin/medicinetype/edit");
        Medicine_type medicinetype = medicine_typeService.get(id);
        mav.addObject("medicinetype", medicinetype);
        return mav;
    }

    @RequestMapping("/manager/medicinetype/delete")
    public String deleteAccountForm(@RequestParam long id, RedirectAttributes re) {
        try {
            medicine_typeService.delete(id);
            re.addFlashAttribute("msg", MessageConstant.DELETE_SUSSCESS);
            return "redirect:/manager/medicinetype";
        }catch (Exception e){
            re.addFlashAttribute("msg_err", MessageConstant.DELETE_ERROR);
            return "redirect:/manager/medicinetype";
        }
    }
}
