package com.coding404.myweb.product.service;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

import java.util.ArrayList;

public interface ProductService {


    int productRegist(ProductVO vo);
//    ArrayList<ProductVO> getList(String prodWriter);
    ArrayList<ProductVO> getList(String prodWriter, Criteria cri);
    int getTotal(String prodWriter, Criteria cri);
    ProductVO getDetail(String prodId);
    void productUpdate(ProductVO vo);
    int productDelete(ProductVO vo);

}

