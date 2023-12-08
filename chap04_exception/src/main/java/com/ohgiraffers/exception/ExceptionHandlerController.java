package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("controller-null") // controller-null 요청을 받음
    public String nullPointerExceptionTest(){
        String str = null;
        System.out.println(str.charAt(0)); // NullPointerException 발생. 리턴값을 넘기지 못하고 process가 멈춤
        return "/main";
    }

    @ExceptionHandler(NullPointerException.class) // controller 레벨에서 예외 처리 (global보다 우선적으로 처리한다.)
    public String nullPointerExceptionHandler(NullPointerException e){
        System.out.println("controller 레벨의 Exception 처리");
        return "error/nullPointer";
    }

    @GetMapping("controller-user")
    public String userException() throws MemberRegistException{
        boolean check = true;
        if (check){
            throw new MemberRegistException("입사가 불가능합니다.");
        }
        return "/";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String memberRegistExceptionHandler(Model model, MemberRegistException e){
        System.out.println("controller 레벨의 memberRegistException 처리");
        model.addAttribute("exception", e);
        return "error/memberRegist";
    }
}
