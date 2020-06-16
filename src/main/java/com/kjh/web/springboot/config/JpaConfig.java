package com.kjh.web.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration//WebMvcTest는 일반적인 @Configuration은 스캔하지 않는다.
@EnableJpaAuditing//JPA Auditing 활성화(DB 등록,수정시 생성시간,수정시간 자동화)
public class JpaConfig {

}
