package com.kjh.web.springboot.web;

import com.kjh.web.springboot.config.auth.LoginUser;
import com.kjh.web.springboot.config.auth.dto.SessionUser;
import com.kjh.web.springboot.service.posts.PostsService;
import com.kjh.web.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    //private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        //SessionUser user = (SessionUser) httpSession.getAttribute("user"); 반복적인 코드 이므로 어노테이션 기반으로 개선
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
        //머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할때 앞의 경로와 확장자는 자동으로 지정된다.
        //src/main/resources/templates/index.mustache로 전환되어 View Resolver가 처리
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}