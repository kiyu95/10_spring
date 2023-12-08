package com.ohgiraffers.pos.menu.dto;

public class MenuDeleteDTO {

    private String name;

    public MenuDeleteDTO() {
    }

    public MenuDeleteDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MenuDeleteDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
