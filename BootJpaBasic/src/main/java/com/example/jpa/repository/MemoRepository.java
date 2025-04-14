package com.example.jpa.repository;

import com.example.jpa.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//<엔티티, ID타입>
public interface MemoRepository extends JpaRepository<Memo, Long> {

    //쿼리메서드 - 메서드의유형을 보고 JPA가 select문을 실행
    //where mno between ? and ?
    List<Memo> findByMnoBetween(Long start, Long end);
    //where text = ? order by mno desc
    List<Memo> findByTextOrderByMnoDesc(String text);
    //where writer in (?, ?, ?) order by mno asc
    List<Memo> findByWriterInOrderByMnoAsc(List<String> list);
    //where mno between ? and ? order by mno desc
    List<Memo> findByMnoBetweenOrderByMnoAsc(Long start, Long end);
    //마지막 매개변수에 Pageable을 넣게되면 페이지 처리를 합니다. (반환도 Page 타입)
    //where text like ? or writer like ?
    Page<Memo> findByTextLikeOrWriterLike(String text, String writer, Pageable pageable);
}
