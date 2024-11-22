package christmas.domain.order;

import christmas.common.dto.OrderCreateDto;
import christmas.common.dto.OrderMenuDto;
import christmas.domain.ParamsValidator;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;

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
	
	public int getMenuTypeCount(MenuType menuType) {
		if (menu.menuType == menuType) {
			return amount;
		}
		return 0;
	}
	
	public int getCost() {
		return menu.calculateTotalCost(amount);
	}
	
	public OrderMenuDto toOrderMenu() {
		return new OrderMenuDto(menu.name(), amount);
	}
}
