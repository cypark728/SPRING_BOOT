package com.simple.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ThymeleafController {

    @GetMapping("/ex01")
    public String ex01() {
        return "view/ex01"; //template 폴터 하위경로
    }

}
