package christmas.domain.order;


import christmas.common.dto.OrderMenuDto;
import christmas.common.dto.OrdersCreateDto;
import christmas.domain.menu.MenuType;

import java.time.LocalDate;
import java.util.List;

public class Orders {
	
	private final List<Order> orders;
	private final LocalDate date;
	
	private Orders(List<Order> orders, LocalDate date) {
		this.orders = orders;
		this.date = date;
	}
	
	public static Orders from(OrdersCreateDto ordersCreateDto) {
		List<Order> orders = ordersCreateDto.orderCreateDtos().stream()
				.map(Order::from)
				.toList();
		
		return new Orders(orders, ordersCreateDto.orderDate());
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public int getMainCount() {
		return orders.stream()
				.mapToInt(order -> order.getMenuTypeCount(MenuType.MAIN))
				.sum();
	}
	
	public int getDessertCount() {
		return orders.stream()
				.mapToInt(order -> order.getMenuTypeCount(MenuType.DESSERT))
				.sum();
	}
	
	//TODO : 총 주문 금액 반환
	public int getTotalCost() {
		return orders.stream()
				.mapToInt(Order::getCost)
				.sum();
	}
	
	//TODO : 주문 메뉴리스트 반환
	public List<OrderMenuDto> getOrderMenus() {
		return orders.stream()
				.map(Order::toOrderMenu)
				.toList();
	}
	
	public int getOrderMonth() {
		return date.getMonthValue();
	}
	
	public int getOrderDay() {
		return date.getDayOfMonth();
	}
}
