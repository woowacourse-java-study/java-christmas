package christmas.domain.order;

import christmas.domain.ParamsValidator;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
import christmas.dto.OrderCreateDto;
import christmas.dto.OrderMenuDto;

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
		return menu.menuType == MenuType.DESSERT;
	}
	
	public boolean isMain() {
		return menu.menuType == MenuType.MAIN;
	}
	
	public int getCost() {
		return menu.cost * amount;
	}
	
	public OrderMenuDto toOrderMenu() {
		return new OrderMenuDto(menu.name(), amount);
	}
}
