package com.example.jpa.repository;

import com.example.jpa.entity.Member;
import com.example.jpa.entity.MemberMemoDTO;
import com.example.jpa.entity.Memo;

import java.util.List;

public interface MemoCustomRepository {

    int updateTest(String writer, Long mno);
    //매니투원조인
    List<Memo> mtoJoin1(long mno);
    //매니투원조인 - 연관관계가 없는 경우 on절
    List<Memo> mtoJoin3(String name);
    //원투매니조인
    Member otmJoin1(String id);
    //DTO로 반환받기
    List<MemberMemoDTO> getList(String id);
    //실습
    List<MemberMemoDTO> quiz(String search);
}
