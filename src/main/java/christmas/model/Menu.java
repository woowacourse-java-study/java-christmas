package christmas.model;

public enum Menu {
    양송이수프(6_000, "애피타이저"),
    타파스(5_500, "애피타이저"),
    시저샐러드(8_000, "애피타이저"),
    티본스테이크(55_000, "메인"),
    바비큐립(54_000, "메인"),
    해산물파스타(35_000, "메인"),
    크리스마스파스타(25_000, "메인"),
    초코케이크(15_000, "디저트"),
    아이스크림(5_000, "디저트"),
    제로콜라(3_000, "음료"),
    레드와인(60_000, "음료"),
    샴페인(25_000, "음료");

    private final int price;
    private final String category;

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
