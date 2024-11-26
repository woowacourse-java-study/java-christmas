package christmas.domain;

import java.math.BigDecimal;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", "애피타이저", BigDecimal.valueOf(6_000)),
    TAPAS("타파스", "애피타이저", BigDecimal.valueOf(5_500)),
    CAESAR_SALAD("시저샐러드", "애피타이저", BigDecimal.valueOf(8_000)),


    T_BONE_STEAK("티본스테이크", "메인", BigDecimal.valueOf(55_000)),
    BBQ_RIB("바비큐립", "메인", BigDecimal.valueOf(54_000)),
    SEAFOOD_PASTA("해산물파스타", "메인", BigDecimal.valueOf(35_000)),
    CHRISTMAS_PASTA("크리스마스파스타", "메인", BigDecimal.valueOf(25_000)),


    CHOCOLATE_CAKE("초코케이크", "디저트", BigDecimal.valueOf(15_000)),
    ICE_CREAM("아이스크림", "디저트", BigDecimal.valueOf(5_000)),


    ZERO_COKE("제로콜라", "음료", BigDecimal.valueOf(3_000)),
    RED_WINE("레드와인", "음료", BigDecimal.valueOf(60_000)),
    CHAMPAGNE("샴페인", "음료", BigDecimal.valueOf(25_000));

    private final String name;
    private final String category;
    private final BigDecimal price;

    Menu(String name, String category, BigDecimal price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public static Menu fromName(String name) {
        for (Menu menu : values()) {
            if (menu.name.equals(name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 메뉴 이름입니다. 다시 입력해 주세요.");
    }
}
