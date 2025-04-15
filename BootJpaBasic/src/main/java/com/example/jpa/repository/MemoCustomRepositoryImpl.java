package com.example.jpa.repository;

import com.example.jpa.entity.Memo;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class MemoCustomRepositoryImpl implements MemoCustomRepository {

    @PersistenceContext //엔티티매니저를 주입받을 때 사용하는 어노테이션
    private EntityManager entityManager;

    @Transactional //update, delete 구문일 경우 부착
    @Override
    public int updateTest(String writer, Long mno) {

        //JPQL
        String sql = "update Memo m set m.writer = :a where m.mno = :b";

        Query query = entityManager.createQuery(sql); // Pstmt
        query.setParameter("a", writer); //a파라미터에 writer 대입
        query.setParameter("b", mno);
        //update, delete문장은 executeUpdate로 실행
        //select문장은 getResultList, getSingleResult 로 실행
        int result = query.executeUpdate();

        return result;
    }

    @Override
    public List<Memo> mtoJoin1(long mno) {
        //select * from memo m1 join member m2 on m1.member_id = m2.id;
        //String sql = "select m from Memo m inner join m.member x where m.mno >= :mno"; //inner join
        //String sql = "select m from Memo m left join m.member x where m.mno >= :mno"; //left join
        String sql = "select m from Memo m right join m.member x where m.mno >= :mno"; //right join

        TypedQuery<Memo> query = entityManager.createQuery(sql, Memo.class);
        query.setParameter("mno", mno);

        List<Memo> result = query.getResultList(); //select구문은 이렇게 실행함

        return result;
    }
}
