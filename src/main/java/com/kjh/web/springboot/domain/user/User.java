package com.kjh.web.springboot.domain.user;

import com.kjh.web.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    //JPA로 데이터베이스에 저장할때 Enum값을 어떤 형태로 저장할지 결정, 기본 int로 된 숫자이다.
    //숫자로 저장되면 데이터베이스로 확인할 때 그 값이 무슨 코드를 의미하는지 알 수 없다. 그래서 문자열로 저장되게끔 선언한다.
    //각 사용자의 권한을 관리할 Enum 클래스 Role을 생성
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
