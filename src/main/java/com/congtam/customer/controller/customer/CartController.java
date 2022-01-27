package com.congtam.customer.controller.customer;

import com.congtam.customer.constant.MessageConstant;
import com.congtam.customer.pojos.Checkup;
import com.congtam.customer.pojos.CheckupDetail;
import com.congtam.customer.pojos.Employee;
import com.congtam.customer.service.CheckupDetailService;
import com.congtam.customer.service.CheckupService;
import com.congtam.customer.service.Medicine_TypeService;
import com.congtam.customer.utils.WritePDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired private ServletContext servletContext;
    @Autowired private CheckupService checkupService;
    @Autowired private CheckupDetailService checkupDetailService;
    @Autowired private Medicine_TypeService categoryService;

    @ModelAttribute
    public void modelAtr(Model model, HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        List<CheckupDetail> list = new ArrayList<>();
        Checkup checkup;
        if (employee!=null){
            checkup = checkupService.findCheckupByUserId(employee.getId());
            if ( checkup!= null && checkup.getStatus()==0){
                list = checkupDetailService.findAllByCheckupDetailId(checkup.getId());
                model.addAttribute("checkup",checkup);
            }
        }else {
            list=null;
        }
        model.addAttribute("listCart",list);
        model.addAttribute("listCategory", categoryService.listAll());
    }

    @RequestMapping("/gio-hang")
    public ModelAndView getCart(){
        ModelAndView mav = new ModelAndView("public/cart");
        return mav;
    }

    @RequestMapping("/gio-hang/del")
    private String del(@RequestParam long id,@RequestParam long checkupId,@RequestParam float total
            ,@RequestParam float checkupTotal
            , RedirectAttributes re){
        try{
            checkupService.editTotal(checkupTotal-total,checkupId);
            checkupDetailService.delete(id);
            re.addFlashAttribute("msg", MessageConstant.DELETE_SUCCESS);
        }catch (Exception e){
            re.addFlashAttribute("msg", MessageConstant.DELETE_ERROR);
            return "redirect:/gio-hang?err=1";
        }
        return "redirect:/gio-hang";
    }

    @RequestMapping("/pay")
    private String pay(@RequestParam long id, RedirectAttributes re, HttpServletRequest request,
                       HttpServletResponse response){
        Checkup checkup = checkupService.get(id);
        WritePDF writePDF =new WritePDF();

        final  String dirPathName = request.getServletContext().getRealPath("/download");
        File dirFile = new File(dirPathName);
        if (!dirFile.exists()){
            dirFile.mkdir();
        }
        String fileName = "HoaDon.pdf";

        String filePathName = dirPathName + File.separator + fileName; //duong đẫn thư mục


        writePDF.write(checkup,filePathName);

        try(OutputStream out = response.getOutputStream()){
            response.setContentType("APPLICATION/OCTET-STREAM");

//            force to download
            response.setHeader("Content-Disposition",
                    "attachment; filename=HoaDon.pdf");

            FileInputStream in = new FileInputStream(filePathName);

            int i;
            while ((i = in.read()) != -1) {
                out.write(i);
            }
            in.close();
            out.flush();
            File oldFile = new File(filePathName);
            if (oldFile.exists()){
                oldFile.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        checkupService.pay(id);
        re.addFlashAttribute("msg",MessageConstant.PAY_SUCCESS);
        return "redirect:/gio-hang";
    }
}
