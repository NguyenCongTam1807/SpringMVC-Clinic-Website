package com.clinic.customer.controller.customer;

import com.clinic.customer.constant.MessageConstant;
import com.clinic.customer.entity.Checkup;
import com.clinic.customer.entity.CheckupDetail;
import com.clinic.customer.entity.Employee;
import com.clinic.customer.service.CheckupDetailService;
import com.clinic.customer.service.CheckupService;
import com.clinic.customer.service.Medicine_TypeService;
import com.clinic.customer.utils.WritePDF;
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
    @Autowired private CheckupService oderService;
    @Autowired private CheckupDetailService oderDetailService;
    @Autowired private Medicine_TypeService categoryService;

    @ModelAttribute
    public void modelAtr(Model model, HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        List<CheckupDetail> list = new ArrayList<>();
        Checkup oder = new Checkup();
        if (employee!=null){
            oder = oderService.findOderByUserId(employee.getId());
            if ( oder!= null && oder.getStatus()==0){
                list = oderDetailService.findAllByOderDetailId(oder.getId());
                model.addAttribute("oder",oder);
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
    private String del(@RequestParam long id,@RequestParam long oderId,@RequestParam float total
            ,@RequestParam float oderTotal
            , RedirectAttributes re){
        try{
            oderService.editTotal(oderTotal-total,oderId);
            oderDetailService.delete(id);
            re.addFlashAttribute("msg", MessageConstant.DELETE_SUSSCESS);
        }catch (Exception e){
            re.addFlashAttribute("msg", MessageConstant.DELETE_ERROR);
            return "redirect:/gio-hang?err=1";
        }
        return "redirect:/gio-hang";
    }

    @RequestMapping("/pay")
    private String pay(@RequestParam long id, RedirectAttributes re, HttpServletRequest request,
                       HttpServletResponse response){
        Checkup oder = oderService.get(id);
        WritePDF writePDF =new WritePDF();

        final  String dirPathName = request.getServletContext().getRealPath("/download");
        File dirFile = new File(dirPathName);
        if (!dirFile.exists()){
            dirFile.mkdir();
        }
        String fileName = "HoaDon.pdf";

        String filePathName = dirPathName + File.separator + fileName; //duong đẫn thư mục


        writePDF.write(oder,filePathName);

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


        System.out.println("thành công");
        oderService.pay(id);
        re.addFlashAttribute("msg",MessageConstant.PAY_SUSSCESS);
        return "redirect:/gio-hang";
    }
}
