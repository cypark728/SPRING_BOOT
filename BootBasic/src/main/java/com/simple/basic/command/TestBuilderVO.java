package com.simple.basic.command;

import lombok.Builder;
import lombok.ToString;

@Builder //빌더패터의 활성화
@ToString
public class TestBuilderVO {

    //builder패턴이란 객체의 불변성을 위해서 사용하는 스프링이 애용하는 문법중의 하나이다.

    //1. 멤버변수
    private String name;
    private int age;

//    //3. 외부클래스가 생성될 떄 내부클래스인 Builder를 매개별수로 받음
//    private TestBuilderVO(Builder builder) {
//        this.name = builder.name;
//        this.age = builder.age;
//    }
//
//    //4. 외부에서 객체 생성을 요구할때 builder메서드를 호출하도록 함
//    public static Builder builder() {
//        return new Builder();
//    }
//
//
//    //2. 내부클래스
//    public static class Builder {
//        private String name;
//        private int age;
//
//        //생성자 제한
//        private Builder() {}
//
//        //5. 빌더클래스 에서는 set메서드만 생성하고, 자신을 반환시킨다.
//        public Builder name(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public Builder age(int age) {
//            this.age = age;
//            return this;
//        }
//
//        //6. build메서드를 호출하면 3번의 생성자를 통해서 멤버변수를 외부에 저장시킵니다.
//        public TestBuilderVO build() {
//            return new TestBuilderVO(this);
//        }
//
//    }
}
