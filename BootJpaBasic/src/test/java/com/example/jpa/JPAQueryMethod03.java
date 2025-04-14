package com.example.jpa;

import com.example.jpa.entity.Memo;
import com.example.jpa.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class JPAQueryMethod03 {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testCode01() {
        List<Memo> list = memoRepository.findByMnoBetween(10L, 20L);
        System.out.println(list.toString());
    }

    @Test
    public void testCode02() {
        List<Memo> list = memoRepository.findByTextOrderByMnoDesc("sample5");
        System.out.println(list.toString());
    }

    @Test
    public void testCode03() {
        List<String> find= Arrays.asList("admin3", "admin84", "admin158");
        List<Memo> list = memoRepository.findByWriterInOrderByMnoAsc(find);
        System.out.println(list.toString());
    }

    @Test
    public void testCode04() {
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoAsc(1L, 20L);
        System.out.println(list.toString());
    }

    @Test
    public void testCode05() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> page = memoRepository.findByTextLikeOrWriterLike("sample123", "admin158", pageable);
        System.out.println("데이터: " + page.getContent());
        System.out.println("총 데이터 수: " + page.getTotalElements());
        System.out.println("총 페이지 수: " + page.getTotalPages());
    }

}
