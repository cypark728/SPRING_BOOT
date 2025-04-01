package com.simple.basic.controller;

import com.simple.basic.command.SimpleVO;
import com.simple.basic.command.TestVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
@RequestMapping("/view")
public class ThymeleafController {

    @GetMapping("/ex01")
    public String ex01() {
        return "view/ex01"; //template 폴터 하위경로
    }

    @GetMapping("/ex02")
    public String ex02(Model model) {
        ArrayList<TestVO> list = new ArrayList<TestVO>();

        for(int i = 1; i <=10; i++) {
            TestVO vo = TestVO
                    .builder()
                    .id("INE" + i)
                    .name("아이네" + i)
                    .address("읍내" + i)
                    .hiredate(LocalDateTime.now())
                    .salary(999)
                    .build();

            list.add(vo);
        }

        model.addAttribute("list", list);

        return "view/ex02"; //template 폴터 하위경로
    }

    @GetMapping("/ex03")
    public String ex03(Model model) {
        ArrayList<TestVO> list = new ArrayList<TestVO>();

        for(int i = 1; i <=10; i++) {
            TestVO vo = TestVO
                    .builder()
                    .id("INE" + i)
                    .name("아이네" + i)
                    .address("읍내" + i)
                    .hiredate(LocalDateTime.now())
                    .salary(999)
                    .build();

            list.add(vo);
        }

        model.addAttribute("list", list);

        return "view/ex03"; //template 폴터 하위경로
    }

    @GetMapping("/result")
    public String result() {
        //request or @RequestParam or VO객체
        return "view/result";
    }

    @GetMapping("/result2/{address}/{value}")
    public String result2(@PathVariable("address") String address,
                          @PathVariable("value") String value) {

        System.out.println(address + "," + value);

        return "view/result";
    }

    @GetMapping("/ex04")
    public String ex04(Model model) {

        TestVO vo = new TestVO();
        vo.setId("INE158");
        vo.setAddress("읍내");
        vo.setName("아이네");
        vo.setHiredate(LocalDateTime.now());

        model.addAttribute("vo", vo);

        return "view/ex04";
    }

    @GetMapping("/ex05")
    public String ex05() {
        return "view/ex05";
    }

    @GetMapping("/ex06")
    public String ex06() {
        return "view/ex06";
    }

    @GetMapping("/quiz01")
    public String quiz01(Model model) {

        SimpleVO vo = SimpleVO
                .builder()
                .no(610)
                .name("주르르")
                .hireDate(LocalDateTime.now())
                .build();
        model.addAttribute("vo", vo);

        return "view/quiz01";
    }

    @GetMapping("/quiz01_result/{no}/{name}/{hireDate}")
    public String quiz01_result(@PathVariable int no,
                                @PathVariable String name,
                                @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hireDate,
                                Model model) {

        SimpleVO vo = new SimpleVO();
        vo.setNo(no);
        vo.setName(name);
        vo.setHireDate(hireDate);
        System.out.println(no + "," + name + "," + hireDate);
        model.addAttribute("vo", vo);

        return "view/quiz01_result";
    }
}
