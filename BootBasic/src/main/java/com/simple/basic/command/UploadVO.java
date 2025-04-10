package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NotBlank
public class UploadVO {
    private String writer;
    private MultipartFile file;
}
