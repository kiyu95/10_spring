package com.ohgiraffers.pos.menu.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public String ExceptionHandler(Exception exception){
//        System.out.println("Global 레벨의 exception 발생");
//        return "error/default";
//    }

    @ExceptionHandler(MenuRegistPriceException.class)
    public String menuRegistPriceException(Model model, MenuRegistPriceException e){
        model.addAttribute("message", "금액은 양수여야 합니다"); // 금액에 음수, 0원 입력헀을때
        return "error/errorMessage";
    }

}
