package com.congtam.customer.controller.admin;

import com.congtam.customer.constant.MessageConstant;
import com.congtam.customer.pojos.Employee;
import com.congtam.customer.pojos.Medicine;
import com.congtam.customer.pojos.Medicine_img;
import com.congtam.customer.pojos.Medicine_type;
import com.congtam.customer.service.MedicineService;
import com.congtam.customer.service.Medicine_TypeService;
import com.congtam.customer.service.Medicine_imgService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller(value = "productControllerOfAdmin")
public class MedicineController {
    @Autowired private MedicineService medicineService;
    @Autowired private Medicine_TypeService medicine_typeService;
    @Autowired private Medicine_imgService medicine_imgService;
    @Autowired private ServletContext context;

    @RequestMapping("/manager/medicine")
    public String home(HttpSession session,Model model) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee!=null && employee.getUserType()==0){
            List<Medicine> listMedicine = medicineService.listAll();
            model.addAttribute("listMedicine", listMedicine);
        }else {
            return "redirect:/dang-nhap";
        }
        return "admin/medicine/index";
    }

    @RequestMapping(value = "/manager/medicine/new", method = RequestMethod.GET)
    public String getMedicineTypes(Map<String, Object> model) {
        Medicine medicine = new Medicine();
        List<Medicine_type> listMedicineType = medicine_typeService.listAll();
        model.put("medicine", medicine);
        model.put("listMedicineType", listMedicineType);
        return "admin/medicine/add";
    }

    @RequestMapping(value = "/manager/medicine/new", method = RequestMethod.POST)
    public String saveMedicine(HttpSession session
            , @Valid @ModelAttribute("medicine") Medicine medicine, BindingResult rs, @RequestParam("file") List<MultipartFile> multipartFileList
            , HttpServletRequest request, RedirectAttributes re) {
        if(rs.hasErrors()) {
            return "admin/medicine/add";
        }
        medicineService.save(medicine);
        try {
                Employee employee = (Employee) session.getAttribute("employee");
                if(employee != null) {
                    if (!multipartFileList.get(0).getOriginalFilename().equals("")) {
                        for (MultipartFile multipartFile:multipartFileList){
                            String fileName = multipartFile.getOriginalFilename();
                            String getFile = getFileNameServer(fileName);
                            File fileRoot = pathFile(getFile,"img",request);
                            try {
                                multipartFile.transferTo(fileRoot);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Medicine_img medicine_img = new Medicine_img();
                            medicine_img.setName(getFile);
                            medicine_img.setMedicine(medicine);
                            medicine_imgService.save(medicine_img);
                        }
                    }
                    re.addFlashAttribute("msg", MessageConstant.ADD_SUCCESS);
                }else {
                    return "redirect:/dang-nhap";
                }
        } catch (Exception e) {
            System.out.println("l???i:====================" + e);
        }
        return "redirect:/manager/medicine";
    }



    @RequestMapping(value = "/manager/medicine/edit", method = RequestMethod.POST)
    public String editMedicineImg(HttpSession session
            , @ModelAttribute("medicine") Medicine medicine, @RequestParam("file") List<MultipartFile> multipartFileList
            , HttpServletRequest request, RedirectAttributes re) {
        Employee employee = (Employee) session.getAttribute("employee");
        if(employee != null) {
            if (!multipartFileList.get(0).getOriginalFilename().equals("")) {
                medicine_imgService.deleteProduct_imgByProductId(medicine.getId());
            }
            Medicine_type medicine_type = medicine_typeService.get(medicine.getMedicine_type().getId());
            medicine.setMedicine_type(medicine_type);
            medicineService.save(medicine);
            try {
                if (!multipartFileList.get(0).getOriginalFilename().equals("")) {
                    for (MultipartFile multipartFile:multipartFileList){
                        String fileName = multipartFile.getOriginalFilename();
                        String getFile = getFileNameServer(fileName);
                        File fileRoot = pathFile(getFile,"img",request);
                        try {
                            multipartFile.transferTo(fileRoot);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Medicine_img medicine_img = new Medicine_img();
                        medicine_img.setName(getFile);
                        medicine_img.setMedicine(medicine);
                        medicine_imgService.save(medicine_img);
                    }
                }
                re.addFlashAttribute("msg", MessageConstant.EDIT_SUCCESS);
            } catch (Exception e) {
                System.out.println("l???i:====================");
            }
        }else {
            return "redirect:/dang-nhap";
        }
        return "redirect:/manager/medicine";
    }


    @RequestMapping("/manager/medicine/edit")
    public ModelAndView editMedicineForm(@RequestParam long id, HttpSession session) {
        ModelAndView mav;
        Employee employee = (Employee) session.getAttribute("employee");
        if(employee != null) {
            mav = new ModelAndView("admin/medicine/edit");
            Medicine medicine = medicineService.get(id);
            List<Medicine_type> listMedicineType = medicine_typeService.listAll();
            List<Medicine_img> listImg = medicine_imgService.getAllByProductId(medicine.getId());
            mav.addObject("listImg",listImg);
            mav.addObject("listMedicineType", listMedicineType);
            mav.addObject("medicine", medicine);
        }else {
            mav= new ModelAndView("login");
        }
        return mav;
    }

    @RequestMapping("/manager/medicine/delete")
    public String deleteArticleForm(@RequestParam long id , RedirectAttributes rs) {
        try{
            medicine_imgService.deleteProduct_imgByProductId(id);
            medicineService.delete(id);
            rs.addFlashAttribute("msg",MessageConstant.DELETE_SUCCESS);
            return "redirect:/manager/medicine";
        }catch (Exception e){
            rs.addFlashAttribute("msg_err",MessageConstant.DELETE_ERROR);
            return "redirect:/manager/medicine";
        }
    }

    //?????t l???i t??n file
    public String getFileNameServer(String fileName){
        if (!StringUtils.isEmpty(fileName)){
            String extention = FilenameUtils.getExtension(fileName);
            String base = FilenameUtils.getBaseName(fileName);
            StringBuilder builder = new StringBuilder();
            builder.append(base).append("-").append(System.nanoTime()).append(".").append(extention);
            return builder.toString();
        }
        return StringUtils.EMPTY;
    }

    public File pathFile(String fileName,String folder,HttpServletRequest request){
        String rootPath = request.getServletContext().getRealPath(""); // tr??? v??? ???????ng d???n tuy???t ?????i c???a web(target)
        File dir = new File(rootPath+folder); // ???????ng d???n folder
        if (!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(rootPath+folder+"/"+fileName);
        return file;
    }
}
