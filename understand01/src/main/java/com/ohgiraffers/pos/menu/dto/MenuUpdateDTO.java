package com.ohgiraffers.pos.menu.dto;

public class MenuUpdateDTO {

    private String name;
    private int price;

    public MenuUpdateDTO() {
    }

    public MenuUpdateDTO(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuUpdateDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
