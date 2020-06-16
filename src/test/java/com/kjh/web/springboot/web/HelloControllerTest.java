package com.kjh.web.springboot.web;

import com.kjh.web.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)//스프링 부트 테스트와 JUnit 사이에 연결자 역할
//선언할 경우 @Controller, @ControllerAdvice등 사용가능 / @Service,@Component,@Repository 사용불가
@WebMvcTest(controllers = HelloController.class,
                excludeFilters = {//CustomOAuth2UserService는 읽을수가 없어, 스캔 대상에서 SecurityConfig를 제거
                    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
                }
            )
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;//웹 API를 테스트할 때 사용, 스프링 MVC 테스트의 시작점

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())//200,404,500등의 상태를 검증, 여기선 OK 즉, 200인지 아닌지를 검증한다.
                .andExpect(content().string(hello));//내용 검증
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

    }

}
