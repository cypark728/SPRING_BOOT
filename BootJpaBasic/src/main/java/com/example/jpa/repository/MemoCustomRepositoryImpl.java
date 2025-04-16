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

    @Transactional //update, delete구문일 경우 부착
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
    
    //매니투원조인
    @Override
    public List<Memo> mtoJoin1(long mno) {
        //inner조인 - 연결되는 데이터가 없으면 안나옴
        //left조인 - 왼쪽은 다나옴
        //right조인 - 오른쪽 다나옴

        //select * from memo m1 join member m2 on m1.member_id = m2.id
        //String sql = "select m from Memo m inner join m.member x where m.mno >= :mno";
        //String sql = "select m from Memo m left join m.member x where m.mno >= :mno";
        String sql = "select m from Memo m right join m.member x where m.mno >= :mno";
        TypedQuery<Memo> query = entityManager.createQuery(sql, Memo.class);
        query.setParameter("mno", mno);
        List<Memo> result = query.getResultList(); //select구문은 이렇게 실행함

        return result;
    }

    @Override
    public List<Memo> mtoJoin3(String name) {
        //값을 맵핑 시킬때는 Memo로 받을 수 있고, Object[]로 받을 수 있음
        String sql = "select m from Memo m inner join Member x on m.writer = x.name where x.name = :name";

        TypedQuery<Memo> query = entityManager.createQuery(sql, Memo.class);
        query.setParameter("name", name);

        return query.getResultList();
    }
}
