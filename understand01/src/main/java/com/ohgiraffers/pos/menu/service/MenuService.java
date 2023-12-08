package com.ohgiraffers.pos.menu.service;

import com.ohgiraffers.pos.menu.dto.MenuDTO;
import com.ohgiraffers.pos.menu.dto.MenuDeleteDTO;
import com.ohgiraffers.pos.menu.dto.MenuInsertDTO;
import com.ohgiraffers.pos.menu.dto.MenuUpdateDTO;
import com.ohgiraffers.pos.menu.exception.MenuRegistPriceException;
import com.ohgiraffers.pos.menu.model.MenuDAO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.awt.*;
import java.util.List;
import java.util.Objects;

@Service
public class MenuService {

    private MenuDAO menuDAO;

    public MenuService(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    public List<MenuDTO> selectAllMenu() {
        List<MenuDTO> menus = menuDAO.selectAllMenu(); // menu를 List형으로 꺼내옴
        if (Objects.isNull(menus)){ // 반환된 결과가 없을 경우
            System.out.println("exceoption menus가 없음");
        }
        return menus; // 꺼내온 menu를 반환
//        for (MenuDTO menuDTO : menus){ // 잘 담아오는지 확인
//            System.out.println(menuDTO);
//        }
    }

    public int registMenu(MenuInsertDTO menuInsertDTO) throws MenuRegistPriceException{

        if (menuInsertDTO.getPrice() <= 0){
            throw new MenuRegistPriceException("금액은 양수여야 합니다.");
        } else {
            int result = menuDAO.registMenu(menuInsertDTO);
            return result;
        }
    }

    public int updateMenu(MenuUpdateDTO menuUpdateDTO) {
        int result = menuDAO.updatePrice(menuUpdateDTO);

        if (result == 0){
            System.out.println("수정 실패");
        }
        return result;
    }

    public int deleteMenu(MenuDeleteDTO menudeleteDTO) {
        int result = menuDAO.deleteMenu(menudeleteDTO);

        if (result == 0){
            System.out.println("삭제 실패");
        }
        return result;
    }
}
