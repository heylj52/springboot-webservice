package com.heylj.book.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// MyBatis의 DAO같은 데이터베이스 접근자. JPA에서는 Repository로 불리고 인터페이스로 생성
// Entity클래스와 기본 Entity Repository는 함께 위치
public interface PostsRepository extends JpaRepository<Posts, Long> {// <Entity클래스, PK타입> 상속 후 기본적인 CRUD 자동생성. @Repository 추가필요X

    // SpringDataJpa가 제공하지 않는 메서드라면 @Queryt사용
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
