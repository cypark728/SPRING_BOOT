package com.simple.basic.config;

import com.simple.basic.controller.HomeController;
import com.simple.basic.util.interceptor.UserAuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //이 클래스를 스프링의 자바설정파일로 쓴다.
@PropertySource("classpath:/application-production.properties")
public class WebConfig implements WebMvcConfigurer {

    @Value("${server.port}")
    private String port; //application.properties 참조

    @Value("${my.example.port}")
    private String myPort; //application-production.properties 참조


    @Autowired
    private ApplicationContext applicationContext; //IOC컨테이너로 동작하는 객체
    
    @Bean //스프링이 이 메소드를 호출 시켜서 반환되는 값을 bean으로 등록시킴
    public void myTest() {

        System.out.println("설정 파일 동작함");

        System.out.println("빈의 개수: " + applicationContext.getBeanDefinitionCount());
        HomeController controller = applicationContext.getBean(HomeController.class);
        System.out.println("ioc컨테이너안에 컨트롤러객체: " + controller);

        System.out.println("application.properties 파일의 port값: " + port);
        System.out.println("application-production.properties 파일의 port값: " + myPort);


    }

    //인터셉터 등록
    @Bean
    public UserAuthHandler userAuthHandler() {
        return new UserAuthHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(userAuthHandler())
                .addPathPatterns("/user/*") //user로 시작하는 경로에서 인터셉터가 동작
                .excludePathPatterns("/user/login") //login페이지는 제외함
                .excludePathPatterns("/user/loginForm");
        //만약 인터셉터가 여러개면 또 추가하면 됨
        //registry.addInterceptor(인터셉터객체)
        //        .addPathPatterns("적용할 경로");
    }
}
