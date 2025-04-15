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

    /// ///////////////////
    //매니 투 원
    @Test
    public void testCode02() {
        List<Memo> lsit= memoRepository.mtoJoin1(2L);
        for(Memo m:lsit){
            System.out.println(m.toString());
        }
    }

}
