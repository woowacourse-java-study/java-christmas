package christmas.domain.menu.impl;

import christmas.domain.menu.Dishes;

public enum Menu implements Dishes {

    양송이스프(6_000, "Appetizer"),
    타파스(5_500, "Appetizer"),
    시저샐러드(8_000, "Appetizer"),

    티본스테이크(55_000, "MainDish"),
    바비큐립(54_000,"MainDish"),
    해산물파스타(35_000, "MainDish"),
    크리스마스파스타(25_000, "MainDish"),

    초코케이크(15_000, "Dessert"),
    아이스크림(5_000, "Dessert"),

    제로콜라(3_000, "Beverage"),
    레드와인(60_000, "Beverage"),
    샴페인(25_000,"Beverage");


    private final int price;
    public final String category;

    Menu(int price, String category) {
        this.price = price;
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
