package com.ohgiraffers.pos.menu.controller;

import com.ohgiraffers.pos.menu.service.MenuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RegistInterceptor implements HandlerInterceptor {

    private final MenuService menuService;

    public RegistInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getParameter("auth").equals("admin")){
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
