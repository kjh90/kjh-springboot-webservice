package com.kjh.web.springboot.web;

import com.kjh.web.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController//컨트롤러를 JSON을 반환하는 컨트롤러(@ResponseBody 각 메소드마다 선언했던 것을 한번에 사용가능하게 해준다.)
public class HelloController {

    @GetMapping("/hello")// = @RequestMapping(method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
