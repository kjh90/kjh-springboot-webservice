package com.kjh.web.springboot.service.posts;

import com.kjh.web.springboot.domain.posts.Posts;
import com.kjh.web.springboot.domain.posts.PostsRepository;
import com.kjh.web.springboot.web.dto.PostsListResponseDto;
import com.kjh.web.springboot.web.dto.PostsResponseDto;
import com.kjh.web.springboot.web.dto.PostsSaveRequestDto;
import com.kjh.web.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    //서비스 개발은 일종의 DAO 계층이라고 보면 된다. 대신 raw 데이터를 가져오는 부분은 Repository로 분리가 된 것이라고 이해하면 된다.
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        //findById : findBy뒤에 컬럼명 검색
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }
    
    //조회
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        //postsRepository 결과로 넘어온 Posts의 stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환하는 메서드
        //.map(posts -> new PostsListResponseDto(posts))
    }

    //삭제
    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);

    }

}
