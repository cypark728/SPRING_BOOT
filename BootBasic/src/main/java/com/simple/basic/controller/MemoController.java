package com.simple.basic.controller;

import com.simple.basic.command.MemoVO;
import com.simple.basic.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/memo")
@RequiredArgsConstructor //롬복 어노테이션
public class MemoController {

//    //멤버변수 주입
//    @Autowired
//    @Qualifier("memoService")
//    private MemoService memoService;

    //생성자 주입
//    @Autowired
//    public MemoController(MemoService memoService) {
//        this.memoService = memoService;
//    }
    private final MemoService memoService; //반드시 final필드로 만들어서 생성

    //글작성화면
    @GetMapping("/memoWrite")
    public String memoWrite() {
        return "memo/memoWrite";
    }

    //글 목록 화면
    @GetMapping("/memoList")
    public String memoList(Model model) {

        //데이터베이스에서 데이터 불러와서 보내기
        model.addAttribute("memoList", memoService.getMemoList());
        return "memo/memoList";
    }

    //글 작성
    @PostMapping("/memoForm")
    public String memoForm(@Valid @ModelAttribute("vo") MemoVO memoVO, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "memo/memoWrite";
        }
        memoService.writeMemo(memoVO);

        return "redirect:/memo/memoList";
    }

}
