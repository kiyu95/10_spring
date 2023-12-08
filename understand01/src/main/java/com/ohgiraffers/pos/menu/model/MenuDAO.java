package com.ohgiraffers.pos.menu.model;

import com.ohgiraffers.pos.menu.dto.MenuDTO;
import com.ohgiraffers.pos.menu.dto.MenuDeleteDTO;
import com.ohgiraffers.pos.menu.dto.MenuInsertDTO;
import com.ohgiraffers.pos.menu.dto.MenuUpdateDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuDAO {

    List<MenuDTO> selectAllMenu();

    int registMenu(MenuInsertDTO menuInsertDTO);

    int updatePrice(MenuUpdateDTO menuUpdateDTO);

    int deleteMenu(MenuDeleteDTO menudeleteDTO);
}
