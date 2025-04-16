package com.example.jpa.repository;

import com.example.jpa.entity.Memo;

import java.util.List;

public interface MemoCustomRepository {

    int updateTest(String writer, Long mno);
    //매니투원조인
    List<Memo> mtoJoin1(long mno);
    //매니투원조인 - 연관관계가 없는 경우 on절
    List<Memo> mtoJoin3(String name);

    //원투매니조인
    

}
