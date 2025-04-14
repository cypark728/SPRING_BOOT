package com.example.jpa.entity;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity //JPA가 관리함
@Table(name = "MEMO") //테이블병
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Memo {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment를 대신함
    //시퀀스에 대한 전략
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
//    @SequenceGenerator(name="my_seq", sequenceName = "my_sequence", allocationSize = 1)
    private long mno;
    @Column(nullable = false, length = 100)
    private String writer;
    @Column(columnDefinition = "varchar(200) default 'Y'")
    private String text;
}
