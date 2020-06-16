package com.kjh.web.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing//JPA Auditing 활성화(DB 등록,수정시 생성시간,수정시간 자동화)
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        //내장 was를 실행(내장 was : 별로도 외부에 was를 두지않고 내부 was를 실행
        //톰캣필요x 스프링 부트로 만들어진 JAR파일(실행가능한 자바 패키지 파일)로 실행하면 된다.)
        SpringApplication.run(Application.class, args);
    }
}
