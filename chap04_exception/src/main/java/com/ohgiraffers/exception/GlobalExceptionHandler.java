package com.ohgiraffers.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 서블릿에서 요청이 왔을때 오류가 발생하면 여기서 처리해줌
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class) // 어떠한 예외를 처리할지 명시해줌
    public String nullPointerExceptionHandler(NullPointerException e){
        System.out.println("Global 레벨의 NullPointerException 처리"); // golbal레벨 : 우리 서버
        return "error/nullPointer";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionHandler(Model model, MemberRegistException exception){
        System.out.println("Global 레벨의 MemberRegistException 처리");
        model.addAttribute("exception", exception);
        return "error/memberRegist";
    }

    @ExceptionHandler(Exception.class) // Exception 예외를 처리할 것을 명시함
    public String nullPointerExceptionHandler(Exception e){
        System.out.println("Global 레벨의 exception 발생함");
        return "error/default";
    }
}
