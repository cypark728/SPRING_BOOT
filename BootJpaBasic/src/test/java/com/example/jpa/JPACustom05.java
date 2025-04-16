package com.example.jpa;

import com.example.jpa.entity.Memo;
import com.example.jpa.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JPACustom05 {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testCode01() {
        int result = memoRepository.updateTest("커스텀레포지토리", 100L);
        System.out.println("업데이트 성공여부:" + result);
    }

    /// ////////////////////////////
    //매니투원 조인
    @Test
    public void testCode02() {
        //List<Memo> list = memoRepository.mtoJoin1(2);
        List<Memo> list = memoRepository.mtoJoin2(2);
        for(Memo m : list) {
            System.out.println(m.toString());
        }
    }

    @Test
    public void testCode03() {
        List<Memo> list = memoRepository.mtoJoin3("홍길동");
        System.out.println(list.toString());
    }






}
