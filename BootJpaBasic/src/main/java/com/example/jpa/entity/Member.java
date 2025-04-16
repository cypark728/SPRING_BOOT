package com.example.jpa.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEMBER")
@EntityListeners(AuditingEntityListener.class) //자동입력되는 날짜
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {

    @Id
    private String id;
    @Column(nullable = false, length = 50)
    private String name;
    //기본으로 지정되는 날짜
    @CreatedDate //JPA를 통해서 날짜가 자동입력되는 어노테이션 (오디팅리스너 랑 함께 사용)
    @Column( updatable = false ) //JPA에 의해서 자동으로 변경되는것을 막음
    private LocalDateTime signDate; //가입일

    //원투매니 조인
    @OneToMany
    @JoinColumn(name = "member_id")
    List<Memo> list = new ArrayList<>();

}
