package christmas.order;

import christmas.ParamsValidator;
import christmas.calendar.Date;

import java.util.List;

public class Orders {
	
	private final List<Order> orders;
	private final Date date;
	
	public Orders(List<Order> orders, Date date) {
		ParamsValidator.validateParamsNotNull(orders, date);
		this.orders = orders;
		this.date = date;
	}
	
	//TODO : 주문한 날짜 반환
	//TODO : 주문 메뉴리스트 반환
	//TODO : 총 주문 금액 반환
	//TODO : 일정 금액 할인 후 총 주문 금액 반환
}
