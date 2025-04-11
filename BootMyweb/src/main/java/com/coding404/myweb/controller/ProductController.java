package com.coding404.myweb.controller;

import com.coding404.myweb.command.ProductUploadVO;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
//        String prodWriter = "admin";
//        ArrayList<ProductVO> list = productService.getList(prodWriter);
//        model.addAttribute("list", list);
//        return "product/productList";
//    }

    @GetMapping("/productList")
    public String productList(Model model,
                              Criteria cri) {

        String prodWriter = "admin";
        ArrayList<ProductVO> list = productService.getList(prodWriter, cri); //데이터
        int total = productService.getTotal(prodWriter, cri); //토탈
        PageVO pageVO = new PageVO(cri, total); //페이지네이션

        model.addAttribute("list", list);
        model.addAttribute("pageVO", pageVO);

        return "product/productList";
    }


    @GetMapping("/productReg")
    public String productReg() {
        return "product/productReg";
    }

    @GetMapping("/productDetail")
    public String productDetail(@RequestParam("prodId") String prodId,
                                Model model) {
        //prodId를 받아서 조회
        ProductVO vo = productService.getDetail(prodId);
        List<ProductUploadVO> voImg = productService.getDetailImage(prodId);
        model.addAttribute("vo", vo);
        model.addAttribute("voImg", voImg);

        return "product/productDetail";
    }

    //등록기능
    @PostMapping("/registForm")
    public String registForm(ProductVO vo,
                             RedirectAttributes ra,
                             @RequestParam("file") List<MultipartFile> list) {

        //1. 리스트안에 multipartfile의 값이 비었으면 제거
        list = list.stream()
                .filter( f -> f.isEmpty() == false )
                .collect(Collectors.toList());

        //2. 이미지 타입인지 검사
        for(MultipartFile file : list) {
            if(file.getContentType().contains("image") == false ) { //이미지가 아니면
                ra.addFlashAttribute("msg", "이미지만 업로드가 가능합니다");
                return "redirect:/product/productList";
            }
        }

        int result = productService.productRegist(vo, list); //vo객체와, 파일리스트
        //1이면 성공, 0이면 실패
        if(result == 1) {
            ra.addFlashAttribute("msg", "정상 등록되었습니다");
        } else {
            ra.addFlashAttribute("msg", "시스템 문제로 인해 등록에 실패했습니다\n1577-1000으로 문의주세요.");
        }


        return "redirect:/product/productList";
    }


    //수정기능
    @PostMapping("/updateForm")
    public String updateForm(ProductVO vo) {
        //log.info( vo.toString() );
        productService.productUpdate(vo);
        //상세화면이 prodId를 필요로하기때문에
        return "redirect:/product/productDetail?prodId=" + vo.getProdId(); //수정하고 컨텐츠화면으로
    }

    /*
    삭제기능
    1. 화면에서는 deleteForm으로 삭제요청이 넘어옵니다. (데이터는 다 넝머옴~)
    2. int productDelete메서드를 이용해서 삭제를 진행하면 됩니다.
    3. 삭제후에는 목록으로 넘어가면 됩니다.
        삭제 성공시 성공msg를 보내주세요.
        삭제 실패시 실패msg를 보내주면 됩니다.
     */
    @PostMapping("/deleteForm")
    public String deleteForm(@RequestParam("prodId") String id,
                             RedirectAttributes ra) {

        int result = productService.productDelete(id);
        if(result == 1) {
            ra.addFlashAttribute("msg", "삭제 되었습니다");
        } else {
            ra.addFlashAttribute("msg", "삭제에 실패했습니다");
        }


        return "redirect:/product/productList";
    }


}









