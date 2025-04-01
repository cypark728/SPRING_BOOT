package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidVO {

    /*
    @NotNull - null제외 - Integer, Long, string등과 같은 wrapper타입에 적용
    @NotEmpty - null제외, 공백허용하지 않음 - Array, Map, String에 적용가능
    @NotBlank - null제외, 공백허용하지 않음, 화이트스페이스 허용 하지 않음 - String에 적용가능

    @Pattern - 정규표현식으로
    @Email - 기본으로 제공되는 이메일 검사
    @Size - 크기 검사... 등등등
    */

    @NotBlank(message = "이름은 필수 입니다.")
    private String name;

    @Email(message = "이메일 형식 이여야 합니다.")
    @NotBlank
    private String email;

    @Pattern(regexp = "[0~9]{3}-[0~9]{4}-[0~9]{4}", message = "휴대폰번호는 010-xxxx-xxxx 유형입니다")
    private String phone;

    @NotNull(message = "급여는 필수 입니다.")
    private Integer salary;
}
