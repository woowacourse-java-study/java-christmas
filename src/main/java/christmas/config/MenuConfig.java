package christmas.config;

import christmas.model.domain.Menu;
import christmas.repository.MenuRepository;

public class MenuConfig {
    public static void initMenus() {
        MenuRepository.addMenu(new Menu("양송이수프", 6_000, "애피타이저"));
        MenuRepository.addMenu(new Menu("타파스", 5_500, "애피타이저"));
        MenuRepository.addMenu(new Menu("시저샐러드", 8_000, "애피타이저"));
        MenuRepository.addMenu(new Menu("티본스테이크", 55_000, "메인"));
        MenuRepository.addMenu(new Menu("바비큐립", 54_000, "메인"));
        MenuRepository.addMenu(new Menu("해산물파스타", 35_000, "메인"));
        MenuRepository.addMenu(new Menu("크리스마스파스타", 25_000, "메인"));
        MenuRepository.addMenu(new Menu("초코케이크", 15_000, "디저트"));
        MenuRepository.addMenu(new Menu("아이스크림", 5_000, "디저트"));
        MenuRepository.addMenu(new Menu("제로콜라", 3_000, "음료"));
        MenuRepository.addMenu(new Menu("레드와인", 60_000, "음료"));
        MenuRepository.addMenu(new Menu("샴페인", 25_000, "음료"));
    }
}
