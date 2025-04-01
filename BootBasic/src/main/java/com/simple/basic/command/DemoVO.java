package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoVO {

    @NotBlank(message = "아이디는 필수 입니다.")
    @Pattern(regexp = "[a-zA-Z0-9]{8,}", message = "아이디는 영문자,숫자 8자 이상입니다.")
    private String id;

    @NotBlank(message = "비밀번호는 필수 입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,}$", message = "비밀번호 영문자,숫자,특수문자 조합 8글자 이상")
    private String pw;
}
