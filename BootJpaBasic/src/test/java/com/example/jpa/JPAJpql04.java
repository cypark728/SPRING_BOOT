package com.example.jpa;

import com.example.jpa.entity.Memo;
import com.example.jpa.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class JPAJpql04 {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testCode01() {
        List<Memo> list = memoRepository.getList();
        System.out.println(list.toString());
    }

    @Test
    public void testCode02() {
        List<Memo> list = memoRepository.getList2(200L);
        System.out.println(list.toString());
    }

    @Test
    public void testCode03() {
        List<Object[]> list = memoRepository.getList3("3");
        for(Object[] obj : list){
            System.out.println(Arrays.toString(obj));
        }
    }

    @Test
    public void testCode04() {
        int result = memoRepository.updateMemo("아이네", 158L);
        System.out.println("업데이트 실행 결과 : " + (result == 1 ? "성공":"실패"));
    }

    @Test
    public void testCode05() {
        Memo memo = Memo.builder()
                    .mno(100L)
                    .text("객체파라미터")
                    .writer("객체하라미터")
                    .build();
        int result = memoRepository.updateMemo2(memo);
        System.out.println("업데이트 실행 결과 : " + (result == 1 ? "성공" : "실패"));
    }

    @Test
    public void testCode06() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> page = memoRepository.getListPage(100L, pageable);
        System.out.println(page.getContent().toString());
    }

    @Test
    public void testCode07() {
        Memo memo = memoRepository.getNative(20L);
        System.out.println(memo);
    }
}
