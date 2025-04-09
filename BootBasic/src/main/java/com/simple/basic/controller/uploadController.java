package com.simple.basic.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/upload")
public class uploadController {

    //화면처리
    @GetMapping("/upload")
    public String upload() {
        return "upload/upload";
    }

    //단일파일 업로드
    @PostMapping("/upload_ok")
    public String upload_ok(MultipartFile file){

        try {
            String originName = file.getOriginalFilename();
            Long size = file.getSize();
            byte[] arr = file.getBytes();
            String contentType = file.getContentType();

            log.info("파일명: " + originName);
            log.info("크기: " + size);
            log.info("파일데이터: " + arr);
            log.info("컨텐츠타입: " + contentType);
        } catch (Exception e) {
            e.printStackTrace();
        }




        return "upload/upload_ok";
    }
}
