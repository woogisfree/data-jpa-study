package study.datajpa.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import study.datajpa.entity.Member;

import java.util.List;

/**
 * 클래스명으로 [원본 Repository 의 이름 + Impl] 을 정확히 생성해야함.
 * 실무에서는 JDBCtemplate, Querydsl 정도를 Custom 해서 사용한다.
 */
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m").getResultList();
    }
}
