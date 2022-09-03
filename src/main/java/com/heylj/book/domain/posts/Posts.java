package com.heylj.book.domain.posts;

import com.heylj.book.domain.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 도메인이란 게시글, 댓글, 회원, 정산, 결제 등 소프트웨어에 대한 요구사항 혹은 문제영역
 * 기존 SQL Mapper, xml의 쿼리담기 -> 도메인에서 해결
 */
@Getter
@NoArgsConstructor
@Entity // JPA 애노테이션. 테이블과 링크될 클래스임을 나타냄. camelCase -> under_score 테이블이름매칭
public class Posts extends BaseTimeEntity {// Entity 클래스에는 절대 Setter 메소드를 만들지 않음!!!! 값 변경 필요시 메소장

    @Id // JPA 애노테이션. 해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA 애노테이션. PK생성규칙. 부트2.0이상에서 auto_increment
    private Long id; // PK는 Long타입의 auto_increment

    @Column(length=500, nullable = false) // 테이블의 칼럼. 안써도 되지만 추가로 변경 필요한 옵션있을 때 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) //타입 TEXT로 변경
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
