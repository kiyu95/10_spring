package com.ohgiraffers.exception;

public class MemberRegistException extends Exception{

    public MemberRegistException(String message) { // 생성자 생성. 메세지 전달받음
        super(message); // 부모에게 메세지 전달
    }
}
