package com.ohgiraffers.pos.menu.controller;

import com.ohgiraffers.pos.menu.dto.MenuDTO;
import com.ohgiraffers.pos.menu.dto.MenuDeleteDTO;
import com.ohgiraffers.pos.menu.dto.MenuInsertDTO;
import com.ohgiraffers.pos.menu.dto.MenuUpdateDTO;
import com.ohgiraffers.pos.menu.exception.MenuRegistPriceException;
import com.ohgiraffers.pos.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller // 사용자의 요청 받아주는 역할
@RequestMapping("menu/*")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/allMenus")
    public ModelAndView selectAllMenu(ModelAndView mv){
        List<MenuDTO> menus = menuService.selectAllMenu();

        if (Objects.isNull(menus)){ //
            System.out.println("exceoption으로 대체한다.");
        }
        mv.addObject("menus", menus);
        mv.setViewName("menu/allMenus"); // allMenus.html과 맵핑
        return mv;
    }

    @GetMapping("registMenu")
    public void regist(){}

    @PostMapping("registMenu") // 새로운 메뉴를 등록하는 메서드
    public ModelAndView registMenu(ModelAndView mv, MenuInsertDTO menuInsertDTO) throws MenuRegistPriceException {
        int result = menuService.registMenu(menuInsertDTO);

        if (result == 0){
            System.out.println("등록 실패");
        } else {
            String message = "신규 메뉴를 등록하였습니다." + menuInsertDTO.toString();

            mv.addObject("message", message);
            mv.setViewName("menu/resultMessage");
        }
        return mv;
    }

    @GetMapping("updateMenu")
    public void update(){}

    @PostMapping("updateMenu")
    public ModelAndView updateMenu(ModelAndView mv, MenuUpdateDTO menuUpdateDTO){
        int result = menuService.updateMenu(menuUpdateDTO);

        if (result == 0){
            System.out.println("수정 실패");
        } else {
            String message = "메뉴의 가격을 수정하였습니다. " + menuUpdateDTO.toString();

            mv.addObject("message", message);
            mv.setViewName("menu/resultMessage");
        }
        return mv;
    }

    @GetMapping("deleteMenu")
    public void deleteMenu(){}

    @PostMapping("deleteMenu")
    public ModelAndView deleteMenu(ModelAndView mv, MenuDeleteDTO menudeleteDTO){
        int result = menuService.deleteMenu(menudeleteDTO);

        if (result == 0){ // 이름 입력 안되었을 경우 exception 확인하기
            if (menudeleteDTO.getName().isBlank()){
                System.out.println("입력이 없습니다.");
            } else {
                System.out.println("삭제 실패");
            }
        } else {
            String message = "메뉴를 삭제하였습니다. " + menudeleteDTO.toString();

            mv.addObject("message", message);
            mv.setViewName("menu/resultMessage");
        }
        return mv;
    }

}