package com.kjh.web.springboot.domain.posts;

import com.kjh.web.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter//게터 메소드 자동생성
@NoArgsConstructor//기본생성자 자동추가
@Entity//테이블과 링크될 클래스, 언더스코어 네이밍으로 테이블 이름을 매칭
public class Posts extends BaseTimeEntity {
    //BaseTimeEntity의 상속으로 등록일/수정일 자동생성(BaseTimeEntity.java 생성과 Application.java의 @EnableJpaAuditing 추가)
    //실제 DB테이블과 매칭될 클래스, Entity클래스라고 한다.
    @Id//ID로 지정된 속성은 테이블의 PRIMARY KEY,
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //PK생성규칙, GenerationType.IDENTITY 옵션을 추가해야만 MYSQL(auto_increment), ORACLE(SEQUENCE) 자동으로 증가
    private Long id;

    @Column(length = 500, nullable = false)//선언하지 않더라도 해당클래스의 필드는 모두 컬럼이 된다. 사용이유는 변경이 필요하면 사용한다.
    private String title;//문자열의 경우 기본값은 VARCHAR2(255), 사이즈 500으로 변경

    @Column(columnDefinition = "TEXT", nullable = false)//타입을 TEXT로 변경
    private String content;
    private String author;
    
    @Builder//해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선인시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
