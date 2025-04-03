package com.coding404.myweb.controller;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.service.ProductService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    //화면처리
//    @GetMapping("/productList")
//    public String productList(Model model) {
//        //셀렉트
//        String prodWriter = "admin";
//        ArrayList<ProductVO> list = productService.getList(prodWriter);
//        model.addAttribute("list", list);
//        return "product/productList";
//    }

    @GetMapping("/productReg")
    public String productReg() {
        return "product/productReg";
    }

    @GetMapping("/productDetail")
    public String productDetail(@RequestParam("prodId") String prodId, Model model) {
        //prodId를 받아서 조회
        ProductVO vo = productService.getDetail(prodId);
        model.addAttribute("vo", vo);

        return "product/productDetail";
    }

    @PostMapping("/registForm")
    public String registForm(ProductVO vo,
                             RedirectAttributes ra) {

        //log.info(vo.toString());
        int result = productService.productRegist(vo);
        //1이면 성공, 0이면 실패

        if(result == 1) { //성공
            ra.addFlashAttribute("msg", "정상 등록 되었습니다.");
        } else {
            ra.addFlashAttribute("msg", "시스템 문제로 인해 등록에 실패했습니다.\n1577-0000으로 문위주세요");
        }

        return "redirect:/product/productList";
    }

    //수정기능
    @PostMapping("/updateForm")
    public String updateForm(ProductVO vo) {
        //log.info(vo.toString());
        productService.productUpdate(vo);

        return "redirect:/product/productDetail?prodId=" + vo.getProdId();
    }

    /*
    삭제기능
    1. 화면에서는 deleteForm으로 삭제요청이 넘어옵니다. (데이터는 다 넘어옴~)
    2. int productDelete메서드를 이용해서 삭제를 진행하면 됩니다.
    3. 삭제후에는 목록으로 넘어가면 됩니다.
       삭제 성공시 성공msg를 보내주세요.
       삭제 실패시 실패msg를 보내주세요.
     */

    @PostMapping("/deleteForm")
    public String deleteForm(ProductVO vo
                            ,RedirectAttributes ra) {
        int result = productService.productDelete(vo);

        if(result == 1) {
            ra.addFlashAttribute("msg", "정상 삭제 되었습니다.");
        } else {
            ra.addFlashAttribute("msg", "삭제 실패했습니다.");
        }
        return "redirect:/product/productList";
    }

    @GetMapping("/productList")
    public String productList(Model model
                            , Criteria cri) {
        //셀렉트
        String prodWriter = "admin";
        ArrayList<ProductVO> list = productService.getList(prodWriter, cri);
        int total = productService.getTotal(prodWriter); //토탈
        PageVO pageVO = new PageVO(cri, total);


        model.addAttribute("list", list);
        model.addAttribute("pageVO", pageVO);

        return "product/productList";
    }
}
