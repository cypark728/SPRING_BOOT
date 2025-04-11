package com.simple.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class SessionController {

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/mypage")
    public String mypage() {
        //인증검사를 인터셉터해서 처리
        return "user/mypage";
    }

    @GetMapping("/success")
    public String success() {
        //인증검사를 인터셉터해서 처리
        return "user/success";
    }

    //로그인시도
    @PostMapping("/loginForm")
    public String loginForm(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session) {
        //디비 로그인 시도...
        //select * from user where username = 값 and password = 값
        if(username.equals(password)) { //성공
            //서버에서 인증수단으로 session발급
            session.setAttribute("user_id", username);
            session.setAttribute("user_name", "아이네");
            return "redirect:/user/success";
        } else {
            return "redirect:/user/login";
        }

    }
}
