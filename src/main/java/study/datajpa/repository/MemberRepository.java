package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    /**
     * NamedQuery
     * @Query 을 생략해도 위에서 선언한 "클래스명.메서드명" 으로 먼저 찾음 -> 메서드 명으로 쿼리 생성
     */
    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);

    /**
     * 정적쿼리
     * 1. 간단한 경우 : findByUsernameAndAgeGreaterThan - 메서드 네이밍으로 쿼리 생성하는 방법
     * 2. 복잡한 경우 : findUser - Query 에 직접 작성
     */
    //기본 조회
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    //값 조회
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    //DTO 조회
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    /**
     * 반환타입
     */
    List<Member> findListByUsername(String username); //컬렉션
    Member findMemberByUsername(String username); //단건
    Optional<Member> findOptionalByUsername(String username); //단건 Optional
}
