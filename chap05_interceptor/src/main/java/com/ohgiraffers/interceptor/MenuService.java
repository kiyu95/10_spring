package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Service;

@Service // springcontext
public class MenuService {

    public void method(){
        System.out.println("메소드 호출 확인");
    }
}
