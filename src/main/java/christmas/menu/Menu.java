package christmas.menu;

import christmas.ParamsValidator;

public class Menu {
	
	private final String name;
	private final int cost;
	private final MenuType menuType;
	
	public Menu(String name, int cost, MenuType menuType) {
		ParamsValidator.validateParamsNotNull(name, menuType);
		validate(name, cost);
		this.name = name;
		this.cost = cost;
		this.menuType = menuType;
	}
	
	private static void validate(String name, int cost) {
		if (name.isBlank()) {
			throw new IllegalArgumentException("메뉴 이름은 비어있을 수 없습니다.");
		}
		if (cost <= 0) {
			throw new IllegalArgumentException("메뉴 가격은 0 이하가 될 수 없습니다.");
		}
	}
	
	//TODO : 특정 구매량에 대한 금액 반환
	//TODO : 프로그램 시작시 자동으로 생성되어야 함
}
