package christmas.domain.order;

import christmas.common.dto.OrderCreateDto;
import christmas.common.dto.OrderMenuDto;
import christmas.common.exception.CustomExceptions;
import christmas.domain.ParamsValidator;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;

public class Order {
	
	private static final int MIN_AMOUNT = 1;
	
	private final Menu menu;
	private final int amount;
	
	private Order(Menu menu, int amount) {
		ParamsValidator.validateParamsNotNull(menu);
		validate(amount);
		this.menu = menu;
		this.amount = amount;
	}
	
	private void validate(int amount) {
		if (amount < MIN_AMOUNT) {
			throw CustomExceptions.INVALID_ORDER.get();
		}
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
	
	public String getMenuName() {
		return menu.name();
	}
	
	public int getCost() {
		return menu.calculateTotalCost(amount);
	}
	
	public OrderMenuDto toOrderMenu() {
		return new OrderMenuDto(menu.name(), amount);
	}
}
