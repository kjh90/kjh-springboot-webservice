package com.kjh.web.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    //MyBatis 에서 Dao라고 불리는 DB Layer 접근자, JPA에선 Repository이며 인터페이스로 생성
    //@Repository를 추가할 필요 없다.
    //Entity클래스와 기본 Entity Repository는 함께 위치해야 한다. 즉 함께 움직여야한다.
    //기존의 DAO패턴을 일부 대체한다. 나머지 일부는 Service 형태로 분리

    @Query("select p from Posts p order by p.id desc")
    List<Posts> findAllDesc();

}
