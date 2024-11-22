package christmas.domain.order;

import christmas.common.dto.OrderCreateDto;
import christmas.common.dto.OrderMenuDto;
import christmas.domain.ParamsValidator;
import christmas.domain.menu.Menu;

public class Order {
	
	private final Menu menu;
	private final int amount;
	
	private Order(Menu menu, int amount) {
		ParamsValidator.validateParamsNotNull(menu);
		this.menu = menu;
		this.amount = amount;
	}
	
	public static Order from(OrderCreateDto dto) {
		return new Order(Menu.from(dto.menuName()), dto.amount());
	}
	
	public boolean isDessert() {
		return menu.isDessert();
	}
	
	public boolean isMain() {
		return menu.isMain();
	}
	
	public int getCost() {
		return menu.calculateTotalCost(amount);
	}
	
	public OrderMenuDto toOrderMenu() {
		return new OrderMenuDto(menu.name(), amount);
	}
}
