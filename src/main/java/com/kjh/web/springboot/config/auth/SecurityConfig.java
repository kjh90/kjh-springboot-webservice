package com.kjh.web.springboot.config.auth;

import com.kjh.web.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity//스프링 시큐리티 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable().headers().frameOptions().disable()//h2-console 화면을 사용하기위해 해당옵션 disable
                .and()
                    .authorizeRequests()//URL별 권한 관리를 설정하는 옵션의 시작점, antMatchers 옵션을 사용하려면 선언필요
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())//permitAll()로 전체열람권한
                    .anyRequest().authenticated()//설정된 값 이외의 나머지 URL들을 나타냄, 인증된 사용자 즉 로그인한 사용자들만 허용
                .and()
                    .logout()
                        .logoutSuccessUrl("/")//로그아웃 성공시 /주소로 이동
                .and()
                    .oauth2Login()//OAuth2 로그인 설정 진입점
                        .userInfoEndpoint()//OAuth2 로그인 성공 이후 사용자 정보를 가져올때의 설정들을 담당
                            .userService(customOAuth2UserService);
                            //소셜 로그인 성공 시 후속 조치를 할 UserService 인터페이스의 구현체를 등록

    }

}
