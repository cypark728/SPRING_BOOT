package com.example.jpa.repository;

import com.example.jpa.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//<엔티티, ID타입>
public interface MemoRepository extends JpaRepository<Memo, Long>,
                                        MemoCustomRepository { /*커스텀 레파지토리*/
    
    //쿼리메서드 - 메서드의유형을 보고 JPA가 select문을 실행
    //where mno between ? and ?
    List<Memo> findByMnoBetween(Long start, Long end);
    //where text = ? order by mno desc
    List<Memo> findByTextOrderByMnoDesc(String text);
    //where writer in (?, ?, ?) order by mno asc
    List<Memo> findByWriterInOrderByMnoAsc(List<String> list);
    //where mno between ? and ? order by mno desc
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long start, Long end);

    //마지막 매개변수에 Pageable을 넣게되면 페이지 처리를 합니다. (반환도 page터압)
    //where text like ? or writer like ?
    Page<Memo> findByTextLikeOrWriterLike(String a, String b, Pageable page);

    /// ///////////////////////////////////////////////////////////
    //JPQL - sql과 유사하나 엔티티를 사용해서 구문을 작성
    //select, update, delete구문을 제공하고, insert는 없음
    @Query("select m from Memo m order by m.mno desc")
    List<Memo> getList();

    @Query("select m from Memo m where m.mno >= :num order by m.text desc")
    List<Memo> getList2(@Param("num") Long num );

    //만약에 select구문을 선별적으로 받으면 Object[]을 사용합니다.
    @Query("select m.mno, m.writer from Memo m where m.writer like %:search%")
    List<Object[]> getList3(@Param("search") String param);
    
    //JPQL - update구문 - @Modifing, @Transactional을 반드시 적어줍니다
    //update 테이블명 set 바꿀값 where 키 = ?
    @Transactional
    @Modifying
    @Query("update Memo m set m.writer = :a where m.mno = :b")
    int updateMemo(@Param("a") String writer, @Param("b") Long mno);

    //객체파라미터를 넘기는 구문
    @Transactional
    @Modifying
    @Query("update Memo m set m.writer = :#{#a.writer }, m.text = :#{#a.text}  where m.mno = :#{#a.mno}")
    int updateMemo2(@Param("a") Memo memo);

    //delete
    @Transactional
    @Modifying
    @Query("delete from Memo m where m.mno = :a")
    int deleteMemo(@Param("a") Long a);

    //JPQL구문에 맨 마지막에 pageable을 넣으면 페이지 처리를 합니다.
    @Query("select m from Memo m where m.mno >= :a")
    Page<Memo> getListPage( @Param("a") Long a, Pageable pageable );

    //네이티브쿼리 - jpql이 아닌 sql문을 직접 날리는 방법
    @Query(value = "select * from memo where mno = ?"
            ,nativeQuery = true)
    Memo getNative(Long a);

//    //jpql로 조인
//    @Query("select m from Memo m inner join m.member x where m.mno >= :mno")
//    List<Memo> mtoJoin2(@Param("mno") long mno);
    
}
