package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class interceptorTestController { // 요청이 들어왔을때의 수행시간을 확인하는 로깅행위, 조작행위, 사용자의 권한 체크 같은 것을 한다

    @PostMapping("stopwatch")
    public String handlerMethod() throws InterruptedException {
        System.out.println("핸들러 메소드 호출함");
        Thread.sleep(1000); // 1초의 대기

        return "result";
    }
}
