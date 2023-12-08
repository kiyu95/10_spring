package com.ohgiraffers.pos.menu.dto;

public class MenuInsertDTO {

    private String name;
    private int price;
    private int categoryCode;

    public MenuInsertDTO() {
    }

    public MenuInsertDTO(String name, int price, int categoryCode) {
        this.name = name;
        this.price = price;
        this.categoryCode = categoryCode;
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

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Override
    public String toString() {
        return "MenuInsertDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categoryCode=" + categoryCode +
                '}';
    }
}
