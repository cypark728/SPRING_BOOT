package com.coding404.myweb.product.service;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    int productRegist(ProductVO vo, List<MultipartFile> files);
    //ArrayList<ProductVO> getList(String prodWriter);
    ArrayList<ProductVO> getList(String prodWriter, Criteria cri);
    int getTotal(String prodWriter, Criteria cri);

    ProductVO getDetail(String prodId);
    void productUpdate(ProductVO vo);
    int productDelete(String prodId);

    //카테고리
    List<CategoryVO> getCategory(); //1단 select
    List<CategoryVO> getCategorySub(CategoryVO vo); //2단, 3단 select

    //파일정보 조회
    List<ProductUploadVO> getDetailImage(String prodId);
}
