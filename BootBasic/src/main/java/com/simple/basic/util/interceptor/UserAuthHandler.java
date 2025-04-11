package com.simple.basic.util.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//1. 핸들러 인터셉터 상속
public class UserAuthHandler implements HandlerInterceptor {

    //2. 오버라이딩
    //pre - 컨트롤러 진입 전에 동작
    //post - 컨트롤러 실행 이후에 동작
    //after completion - resolver view 까지 실행된 이후에 동작

    //3. 스프링 설정 파일에 interceptor 등록

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //return에 true 가 들어가면 controller 실행함, false가 들어가면 controller 실행 안함.

        //리퀘스트에서 세션 얻음
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("user_id"); //로그인 만든 사람이 지정한 값

        if(userId == null) {
            //인증되지 않음
            response.sendRedirect("/user/login"); //로그인 페이지로
            return false; //컨트롤러 실행하지 않음
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("컨트롤러 실행후 인터셉터 동작");
    }
}
