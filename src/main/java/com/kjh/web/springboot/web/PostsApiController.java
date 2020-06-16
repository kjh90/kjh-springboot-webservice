package com.kjh.web.springboot.web;

import com.kjh.web.springboot.service.posts.PostsService;
import com.kjh.web.springboot.web.dto.PostsResponseDto;
import com.kjh.web.springboot.web.dto.PostsSaveRequestDto;
import com.kjh.web.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor//final이나 @NonNull인 필드값만 파라미터로 받는 생성자를 만들어준다.
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        //@PathVariable : url을 처리할떄 사용
        //@RequestParam : url뒤에 붙는 파라미터값을 가져올때 사용, http://~~~?index=1
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}
