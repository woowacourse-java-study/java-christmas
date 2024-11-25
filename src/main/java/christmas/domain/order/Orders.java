package christmas.domain.order;


import christmas.common.dto.OrderMenuDto;
import christmas.common.dto.OrdersCreateDto;
import christmas.common.exception.CustomExceptions;
import christmas.domain.menu.MenuType;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Orders {
	
	private static final int MIN_ORDER_COST = 10000;
	private static final int MAX_ORDER_COUNT = 20;
	
	private final List<Order> orders;
	private final LocalDate date;
	
	private Orders(List<Order> orders, LocalDate date) {
		validate(orders);
		this.orders = orders;
		this.date = date;
	}
	
	private void validate(List<Order> orders) {
		validateNoDuplication(orders);
		validateMinCost(orders);
		validateNotOnlyDrink(orders);
		validateOrderCount(orders);
	}
	
	private static void validateNoDuplication(List<Order> orders) {
		Set<String> names = orders.stream()
				.map(Order::getMenuName)
				.collect(Collectors.toSet());
		
		if (names.size() != orders.size()) {
			throw CustomExceptions.INVALID_ORDER.get();
		}
	}
	
	private static void validateMinCost(List<Order> orders) {
		int totalCost = orders.stream()
				.mapToInt(Order::getCost)
				.sum();
		if (totalCost < MIN_ORDER_COST) {
			throw CustomExceptions.INVALID_ORDER.get();
		}
	}
	
	private void validateNotOnlyDrink(List<Order> orders) {
		boolean isAllDrink = true;
		for (Order order : orders) {
			if (!order.isDrink()) {
				isAllDrink = false;
				break;
			}
		}
		
		if (isAllDrink) {
			throw CustomExceptions.INVALID_ORDER.get();
		}
	}
	
	private void validateOrderCount(List<Order> orders) {
		int totalOrderCount = orders.stream()
				.mapToInt(Order::getAmount)
				.sum();
		
		if (totalOrderCount > MAX_ORDER_COUNT) {
			throw CustomExceptions.INVALID_ORDER.get();
		}
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
	
	public int getTotalCost() {
		return orders.stream()
				.mapToInt(Order::getCost)
				.sum();
	}
	
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
