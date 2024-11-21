package christmas.order;


import java.time.LocalDate;
import java.util.List;

public class Orders {
	
	private final List<Order> orders;
	private final LocalDate date;
	
	public Orders(List<Order> orders, LocalDate date) {
		this.orders = orders;
		this.date = date;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	//TODO : 주문한 날짜 반환
	//TODO : 주문 메뉴리스트 반환
	//TODO : 총 주문 금액 반환
	//TODO : 일정 금액 할인 후 총 주문 금액 반환
}
