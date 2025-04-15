package com.example.jpa.repository;

import com.example.jpa.entity.Memo;

import java.util.List;

public interface MemoCustomRepository {

    int updateTest(String writer, Long mno);
    //매니 투 원 조인
    List<Memo> mtoJoin1(long mno );
}
