//package com.clinic.customer.controller.customer;
//
//import com.karaoke.customer.entity.Oder;
//import com.karaoke.customer.entity.OderDetail;
//import com.karaoke.customer.entity.User;
//import com.karaoke.customer.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class SearchController {
//
//    @Autowired private CategoryService categoryService;
//    @Autowired private ProductService productService;
//    @Autowired private OderService oderService;
//    @Autowired private OderDetailService oderDetailService;
//
//    @ModelAttribute
//    public void modelAtr(Model model, HttpSession session){
//        User user = (User) session.getAttribute("user");
//        List<OderDetail> list = new ArrayList<>();
//        Oder oder = new Oder();
//        if (user!=null){
//            oder = oderService.findOderByUserId(user.getId());
//            if ( oder!= null && oder.getStatus()==0){
//                list = oderDetailService.findAllByOderDetailId(oder.getId());
//                model.addAttribute("oder",oder);
//            }
//        }else {
//            list=null;
//        }
//        model.addAttribute("listCart",list);
//        model.addAttribute("listCategory", categoryService.listAll());
//    }
//
//    @RequestMapping(value = "tim-kiem",method = RequestMethod.POST)
//    public ModelAndView search(@RequestParam("search")String search){
//        ModelAndView mav = new ModelAndView("public/c-product");
//        mav.addObject("name","Tìm tin");
//        mav.addObject("listById",productService.search(search));
//        return mav;
//    }
//
//}
