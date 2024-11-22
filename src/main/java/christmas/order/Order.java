package christmas.order;

import christmas.menu.Menu;
import christmas.ParamsValidator;
import christmas.menu.MenuType;

public class Order {
	
	private final Menu menu;
	private final int amount;
	
	public Order(Menu menu, int amount) {
		ParamsValidator.validateParamsNotNull(menu);
		this.menu = menu;
		this.amount = amount;
	}
	
	public boolean isDessert() {
		return menu.menuType == MenuType.DESSERT;
	}
	
	public boolean isMain() {
		return menu.menuType == MenuType.MAIN;
	}
	
	// TODO : 현재 주문량에 대한 금액 반환
	public int getCost() {
		return menu.cost * amount;
	}
}
