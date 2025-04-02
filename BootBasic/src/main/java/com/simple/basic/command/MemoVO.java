package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoVO {

    private Integer mno;

    @NotBlank(message = "메모를 입력해 주세요.")
    @Size(min = 5, message = "5글자 이상입니다.")
    private String memo;

    @NotBlank(message = "번호를 입력해 주세요.")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "휴대폰번호는 010-xxxx-xxxx 유형입니다")
    private String phone;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Pattern(regexp = "[0-9]{4}", message = "비밀번호는 숫자 4자리 입니다.")
    private String pw;

    private String secret;

}
