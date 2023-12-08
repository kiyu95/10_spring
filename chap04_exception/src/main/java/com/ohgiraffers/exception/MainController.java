package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/main"}) // 기본요청과 main 요청이 들어왔을때 처리해줄 서블릿
    public String main(){
        return "main";
    }
}
