package christmas.domain.menu;

import java.util.Arrays;

/*
<애피타이저>
양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)

<메인>
티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)

<디저트>
초코케이크(15,000), 아이스크림(5,000)

<음료>
제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
 */
public enum Menu {
	
	양송이스프(6_000, MenuType.APPETIZER),
	타파스(5_500, MenuType.APPETIZER),
	시저샐러드(8_000, MenuType.APPETIZER),
	
	티본스테이크(55_000, MenuType.MAIN),
	바비큐립(54_000, MenuType.MAIN),
	해산물파스타(35_000, MenuType.MAIN),
	크리스마스파스타(25_000, MenuType.MAIN),
	
	초코케이크(15_000, MenuType.DESSERT),
	아이스크림(5_000, MenuType.DESSERT),
	
	제로콜라(5_000, MenuType.DRINK),
	레드와인(60_000, MenuType.DRINK),
	샴페인(25_000, MenuType.DRINK),
	;
	
	public final int cost;
	public final MenuType menuType;
	
	Menu(int cost, MenuType menuType) {
		this.cost = cost;
		this.menuType = menuType;
	}
	
	public static Menu from(String name) {
		return Arrays.stream(Menu.values())
				.filter(menu -> menu.name().equals(name))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
	
	//TODO : 특정 구매량에 대한 금액 반환
	public int calculateTotalCost(int purchaseAmount) {
		return cost * purchaseAmount;
	}
}
