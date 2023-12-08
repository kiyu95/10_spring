package com.ohgiraffers.pos.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("menu/*") //
public class InterceptorController {

    @GetMapping("registInterceptor")
    public void registInterceptor(){}

    @PostMapping("registInterceptor")
    public String registHandlerMethod() throws InterruptedException {
        Thread.sleep(1000);

        return "src/main/resources/templates/menu/registMenu";
    }
}
