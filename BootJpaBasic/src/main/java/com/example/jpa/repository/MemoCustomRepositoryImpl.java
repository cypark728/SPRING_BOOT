package com.example.jpa.repository;

import com.example.jpa.entity.Member;
import com.example.jpa.entity.MemberMemoDTO;
import com.example.jpa.entity.Memo;
import com.example.jpa.entity.QMemo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class MemoCustomRepositoryImpl implements MemoCustomRepository {

    @PersistenceContext //엔티티매니저를 주입받을 때 사용하는 어노테이션
    private EntityManager entityManager;

    //쿼리dsl
    private JPAQueryFactory jpaQueryFactory;
    //생성될때 엔티티매니저를 받아서 초기화
    public MemoCustomRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

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

        return query.getResultList(); //여러행
    }


    @Override
    public Member otmJoin1(String id) {

        String sql = "select m from Member m inner join m.list x where m.id = :id";

        TypedQuery<Member> query = entityManager.createQuery(sql, Member.class);
        query.setParameter("id", id);

        Member m = query.getSingleResult(); //1행으로 쿼리함
        return m;
    }

    //dto로 받기
    @Override
    public List<MemberMemoDTO> getList(String id) {

        String sql = "select new com.example.jpa.entity.MemberMemoDTO(x.id, x.name, x.signDate, m.mno, m.writer, m.text)" +
                "from Memo m inner join m.member x where x.id = :id";

        TypedQuery<MemberMemoDTO> query = entityManager.createQuery(sql, MemberMemoDTO.class);
        query.setParameter("id", id);

        return query.getResultList();
    }

    @Override
    public List<MemberMemoDTO> quiz(String search) {
        //SELECT * FROM member m LEFT JOIN memo x ON x.member_id = m.id WHERE x.text LIKE '%sample%';
        String sql = "select new com.example.jpa.entity.MemberMemoDTO(m.id, m.name, m.signDate, x.mno, x.writer, x.text) from Member m " +
                "left join m.list x where x.text like :search";

        TypedQuery<MemberMemoDTO> query = entityManager.createQuery(sql, MemberMemoDTO.class);
        query.setParameter("search", "%" + search + "%");

        return query.getResultList();
    }

    //쿼리dsl
    @Override
    public Memo selectDsl() {
        QMemo memo = QMemo.memo;
        //jpaQueryFactory으로 sql을 작성
        //select * from memo where mno = 10 order by text desc
        Memo result = jpaQueryFactory.select(memo)
                .from(memo)
                .where(memo.mno.eq(10L) )
                .orderBy(memo.text.desc())
                .fetchOne(); //1행 반환시킴
        return result;
    }

    @Override
    public List<Memo> selectDsl2() {

        QMemo memo = QMemo.memo;

        List<Memo> list = jpaQueryFactory.select(memo)
                .from(memo)
                //.where(memo.text.like("%2%")) //where text like '%2%'
                //.where(memo.mno.gt(10).and(memo.mno.lt(20)) ) //where mno > 10 and mno < 20
                .where(memo.mno.loe(10).or(memo.mno.goe(20))) //where mno <= 10 or mno >= 20
                .orderBy( memo.mno.asc())
                .fetch(); //여러행을 조회함

        return list;
    }

    @Override
    public List<Memo> selectDsl3(String searchType, String searchName) {

        QMemo memo = QMemo.memo;
        //조건을 걸기위한 불린빌더 객체
        BooleanBuilder builder = new BooleanBuilder();

        //writer검색이었다면~
        if(searchType != null && searchType.equals("writer") ) {
            builder.and( memo.writer.like("%" +searchName + "%" ) );
        }
        //text검색이었다면~
        if(searchType != null && searchType.equals("text")) {
            builder.and( memo.text.like("%" + searchName + "%") );
        }

        List<Memo> list = jpaQueryFactory.select(memo)
                .from(memo)
                .where(builder)
                .fetch();

        return list;
    }
}
