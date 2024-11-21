package christmas.order;

import christmas.menu.Menu;
import christmas.ParamsValidator;

public class Order {
	
	private final Menu menu;
	private final int amount;
	
	public Order(Menu menu, int amount) {
		ParamsValidator.validateParamsNotNull(menu);
		this.menu = menu;
		this.amount = amount;
	}
	
	// TODO : 현재 주문량에 대한 금액 반환
}
